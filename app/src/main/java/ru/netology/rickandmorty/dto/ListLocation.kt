package ru.netology.rickandmorty.dto

import com.google.gson.annotations.SerializedName

data class ListLocation(
    @SerializedName("info")
    val info: InfoLocation,
    @SerializedName("results")
    val results: List<Location>
)

data class InfoLocation(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: Int?
)
data class Location(
    @SerializedName("id")
    val id: Int, //	The id of the location.
    @SerializedName("name")
    val name: String, //	The name of the location.
    @SerializedName("type")
    val type: String, //	The type of the location.
    @SerializedName("dimension")
    val dimension: String, //	The dimension in which the location is located.
    @SerializedName("residents")
    val residents: List<String>, // (urls)	List of character who have been last seen in the location.
    @SerializedName("url")
    val url: String, // (url)	Link to the location's own endpoint.
    @SerializedName("created")
    val created: String, //	Time at which the location was created in the database.
)