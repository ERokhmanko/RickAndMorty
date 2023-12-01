package ru.netology.rickandmorty.dto

import com.google.gson.annotations.SerializedName

data class ListEpisode(
    @SerializedName("info")
    val info: InfoEpisode,
    @SerializedName("results")
    val results: List<Episode>
)

data class InfoEpisode(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)

data class Episode(
    @SerializedName("id")
    val id: Int, //	The id of the episode.
    @SerializedName("name")
    val name: String, //	The name of the episode.
    @SerializedName("air_date")
    val airDate: String, //	The air date of the episode.
    @SerializedName("episode")
    val episode: String, //	The code of the episode.
    @SerializedName("characters")
    val characters: List<String>, // (urls)	List of characters who have been seen in the episode.
    @SerializedName("url")
    val url: String, // (url)	Link to the episode's own endpoint.
    @SerializedName("created")
    val created: String, //	Time at which the episode was created in the database.
)