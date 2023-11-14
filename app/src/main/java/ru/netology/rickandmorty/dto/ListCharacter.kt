package ru.netology.rickandmorty.dto

import com.google.gson.annotations.SerializedName

data class ListCharacter(
    @SerializedName("info")
    val info: InfoCharacter,
    @SerializedName("results")
    val results: List<Character>
)

data class InfoCharacter(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)

data class Character(
    @SerializedName("id")
    val id: Int, //The id of the character.
    @SerializedName("name")
    val name: String, //The name of the character.
    @SerializedName("status")
    val status: String, //The status of the character ('Alive', 'Dead' or 'unknown').
    @SerializedName("species")
    val species: String, //The species of the character.
    @SerializedName("type")
    val type: String, //The type or subspecies of the character.
    @SerializedName("gender")
    val gender: String, //The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
    @SerializedName("origin")
    val origin: Origin, //Name and link to the character's origin location.
    @SerializedName("location")
    val location: LocationCharacter, //Name and link to the character's last known location endpoint.
    @SerializedName("image")
    val image: String, //Link to the character's image. All images are 300x300px and most are medium
    // shots or portraits since they are intended to be used as avatars.
    @SerializedName("episode")
    val episode: List<String>, //List of episodes in which this character appeared.
    @SerializedName("url")
    val url: String, //Link to the character's own URL endpoint.
    @SerializedName("created")
    val created: String //Time at which the character was created in the database.

)

data class Origin(
    val name: String,
    val url: String
)

data class LocationCharacter(
    val name: String,
    val url: String
)