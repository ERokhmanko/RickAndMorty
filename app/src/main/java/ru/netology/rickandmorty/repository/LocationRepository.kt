package ru.netology.rickandmorty.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.netology.rickandmorty.api.ApiService
import ru.netology.rickandmorty.dao.LocationDao
import ru.netology.rickandmorty.dao.NextPageLocationDao
import ru.netology.rickandmorty.dto.Location
import ru.netology.rickandmorty.entity.LocationEntity
import ru.netology.rickandmorty.extensions.PageSize
import ru.netology.rickandmorty.repository.remoteMediator.LocationRemoteMediator
import javax.inject.Inject

class LocationRepository @Inject constructor(
    api: ApiService,
    daoNextPage: NextPageLocationDao,
    private val daoLocation: LocationDao
) {
    @OptIn(ExperimentalPagingApi::class)
    val data: Flow<PagingData<Location>> = Pager(
        config = PagingConfig(
            pageSize = PageSize.PAGE_SIZE,
            enablePlaceholders = false
        ),
        remoteMediator = LocationRemoteMediator(
            daoNextPage = daoNextPage,
            daoLocation = daoLocation,
            api = api
        ),
        pagingSourceFactory = { daoLocation.getPagingSourceLocation() }
    ).flow
        .map {
            it.map(LocationEntity::toDto)
        }
}