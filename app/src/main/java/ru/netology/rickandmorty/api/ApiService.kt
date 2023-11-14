package ru.netology.rickandmorty.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.rickandmorty.dto.ListCharacter
import ru.netology.rickandmorty.dto.ListEpisode
import ru.netology.rickandmorty.dto.ListLocation

interface ApiService {

    //Character
    @GET("character")
    suspend fun getPageCharacters(@Query("page") page: String? = null): Response<ListCharacter>

    //Location
    @GET("location")
    suspend fun getAllLocations(): Response<ListLocation>

    //Episode
    @GET("episode")
    suspend fun getAllEpisodes(): Response<ListEpisode>

}