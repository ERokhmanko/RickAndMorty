package ru.netology.rickandmorty.repository.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import retrofit2.HttpException
import ru.netology.diploma.error.ApiError
import ru.netology.rickandmorty.api.ApiService
import ru.netology.rickandmorty.dao.LocationDao
import ru.netology.rickandmorty.dao.NextPageLocationDao
import ru.netology.rickandmorty.entity.LocationEntity
import ru.netology.rickandmorty.entity.NextPageLocationEntity
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationRemoteMediator @Inject constructor(
    private val daoNextPage: NextPageLocationDao,
    private val daoLocation: LocationDao,
    private val api: ApiService

) : RemoteMediator<Int, LocationEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>
    ): MediatorResult {
        try {
            val result = when (loadType) {
                LoadType.APPEND -> {
                    if (state.isEmpty()) {
                        api.getAllLocations()
                    } else {
                        val nextPage = daoNextPage.getLast() ?: return MediatorResult.Success(false)
                        api.getAllLocations(nextPage)
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

            daoNextPage.insert(NextPageLocationEntity.fromDto(nextPage))

            daoLocation.insert(
                data.results.map {
                    LocationEntity.fromDto(it)
                }
            )

            return MediatorResult.Success(data.results.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }
    }
}