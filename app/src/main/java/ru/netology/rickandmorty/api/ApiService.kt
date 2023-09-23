package ru.netology.rickandmorty.api

import retrofit2.Response
import retrofit2.http.GET
import ru.netology.rickandmorty.dto.ListCharacter

interface ApiService {

//Character
    @GET("character")
suspend fun getAllCharacters() : Response<ListCharacter>


}