package ru.netology.rickandmorty.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.netology.rickandmorty.api.ApiService
import ru.netology.rickandmorty.dao.EpisodeDao
import ru.netology.rickandmorty.dao.NextPageEpisodeDao
import ru.netology.rickandmorty.dto.Episode
import ru.netology.rickandmorty.entity.EpisodeEntity
import ru.netology.rickandmorty.extensions.PageSize
import ru.netology.rickandmorty.repository.remoteMediator.EpisodeRemoteMediator
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val daoEpisode: EpisodeDao,
    api: ApiService,
    daoPage: NextPageEpisodeDao
) {
    @OptIn(ExperimentalPagingApi::class)
    val data: Flow<PagingData<Episode>> = Pager(
        config = PagingConfig(
            pageSize = PageSize.PAGE_SIZE,
            enablePlaceholders = false
        ),
        remoteMediator = EpisodeRemoteMediator(
            daoEpisode = daoEpisode,
            api = api,
            daoNextPage = daoPage
        ),
        pagingSourceFactory = { daoEpisode.getPagingSourceEpisode() }
    ).flow
        .map {
            it.map(EpisodeEntity::toDto)
        }
}