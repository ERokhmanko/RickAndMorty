package ru.netology.rickandmorty.api

import retrofit2.Response
import retrofit2.http.GET
import ru.netology.rickandmorty.dto.ListCharacter
import ru.netology.rickandmorty.dto.ListEpisode
import ru.netology.rickandmorty.dto.ListLocation

interface ApiService {

//Character
    @GET("character")
suspend fun getAllCharacters() : Response<ListCharacter>

//Location
    @GET("location")
    suspend fun getAllLocatoins() : Response<ListLocation>

    //Episode
    @GET("episode")
    suspend fun getAllEpisode():Response<ListEpisode>

}