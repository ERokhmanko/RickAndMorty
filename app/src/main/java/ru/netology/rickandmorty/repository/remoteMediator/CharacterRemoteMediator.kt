package ru.netology.rickandmorty.repository.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import retrofit2.HttpException
import ru.netology.diploma.error.ApiError
import ru.netology.rickandmorty.api.ApiService
import ru.netology.rickandmorty.dao.CharacterDao
import ru.netology.rickandmorty.dao.NextPageCharacterDao
import ru.netology.rickandmorty.entity.CharacterEntity
import ru.netology.rickandmorty.entity.NextPageCharacterEntity
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val daoCharacter: CharacterDao,
    private val daoNextPage: NextPageCharacterDao,
    private val api: ApiService

) : RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        try {
            val result = when (loadType) {

                LoadType.APPEND -> {
                    if (state.isEmpty()) {
                        api.getPageCharacters()
                    } else {
                        val page = daoNextPage.getLast() ?: return MediatorResult.Success(false)
                        api.getPageCharacters(page)
                    }
                }

                LoadType.REFRESH -> {
                    return MediatorResult.Success(false)
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(false)
                }
            }

            if (!result.isSuccessful) throw HttpException(result)

            val data = result.body() ?: throw ApiError(
                result.message()
            )

            daoCharacter.insert(
                data.results.map {
                    CharacterEntity.fromDto(it)
                }
            )

            val urlNextPage = data.info.next
            val regex = Regex("\\d+")
            val nextPage = urlNextPage?.let { regex.find(it)?.value }

            daoNextPage.insert(
                NextPageCharacterEntity.fromDto(nextPage)
            )

            return MediatorResult.Success(data.results.isEmpty())

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }
    }
}