package ru.netology.rickandmorty.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.netology.rickandmorty.api.ApiService
import ru.netology.rickandmorty.dao.CharacterDao
import ru.netology.rickandmorty.dao.NextPageCharacterDao
import ru.netology.rickandmorty.dto.Character
import ru.netology.rickandmorty.entity.CharacterEntity
import ru.netology.rickandmorty.extensions.PageSize
import ru.netology.rickandmorty.repository.remoteMediator.CharacterRemoteMediator
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val daoCharacter: CharacterDao,
    daoPage: NextPageCharacterDao,
    api: ApiService,
) {
    @OptIn(ExperimentalPagingApi::class)
    val data: Flow<PagingData<Character>> = Pager(
        config = PagingConfig(
            pageSize = PageSize.PAGE_SIZE,
            enablePlaceholders = false
        ),
        remoteMediator = CharacterRemoteMediator(
            daoCharacter = daoCharacter,
            daoNextPage = daoPage,
            api = api
        ),
        pagingSourceFactory = { daoCharacter.getPagingSourceCharacter() }
    ).flow
        .map {
            it.map(CharacterEntity::toDto)
        }
}

