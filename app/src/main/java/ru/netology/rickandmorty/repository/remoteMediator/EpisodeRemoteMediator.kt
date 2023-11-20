package ru.netology.rickandmorty.repository.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import retrofit2.HttpException
import ru.netology.diploma.error.ApiError
import ru.netology.rickandmorty.api.ApiService
import ru.netology.rickandmorty.dao.EpisodeDao
import ru.netology.rickandmorty.dao.NextPageEpisodeDao
import ru.netology.rickandmorty.entity.EpisodeEntity
import ru.netology.rickandmorty.entity.NextPageEpisodeEntity
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EpisodeRemoteMediator @Inject constructor(
    private val daoEpisode: EpisodeDao,
    private val daoNextPage: NextPageEpisodeDao,
    private val api: ApiService

) : RemoteMediator<Int, EpisodeEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeEntity>
    ): MediatorResult {
        try {
            val result = when (loadType) {

                LoadType.APPEND -> {
                    if (state.isEmpty()) {
                        api.getAllEpisodes()
                    } else {
                        val nextPage = daoNextPage.getLast() ?: return MediatorResult.Success(false)
                        api.getAllEpisodes(nextPage)
                    }
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(false)
                }

                LoadType.REFRESH -> {
                    return MediatorResult.Success(false)
                }
            }

            if (!result.isSuccessful) throw HttpException(result)

            val data = result.body() ?: throw ApiError(
                result.message()
            )

            val urlNextPage = data.info.next
            val regex = Regex("\\d+")
            val nextPage = urlNextPage?.let { regex.find(it)?.value }

            daoNextPage.insert(
                NextPageEpisodeEntity.fromDto(nextPage)
            )

            daoEpisode.insert(
                data.results.map {
                    EpisodeEntity.fromDto(it)
                }
            )

            return MediatorResult.Success(data.results.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }
    }
}