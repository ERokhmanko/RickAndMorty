package ru.netology.rickandmorty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.rickandmorty.dto.InfoLocation
import ru.netology.rickandmorty.dto.Location

@Entity
data class InfoLocationEntity(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Int?
) {
    fun toDto() = InfoLocation(count, pages, next, prev)

    companion object {
        fun fromDto(dto: InfoLocation) =
            InfoLocationEntity(dto.count, dto.pages, dto.next, dto.prev)
    }
}

@Entity
data class LocationEntity(
    @PrimaryKey
    val id: Int, //	The id of the location.
    val name: String, //	The name of the location.
    val type: String, //	The type of the location.
    val dimension: String, //	The dimension in which the location is located.
    val residents: List<String>, // (urls)	List of character who have been last seen in the location.
    val url: String, // (url)	Link to the location's own endpoint.
    val created: String, //	Time at which the location was created in the database.
) {
    fun toDto() = Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents,
        url = url,
        created = created
    )

    companion object {
        fun fromDto(dto: Location) = LocationEntity(
            id = dto.id,
            name = dto.name,
            type = dto.type,
            dimension = dto.dimension,
            residents = dto.residents,
            url = dto.url,
            created = dto.created
        )
    }
}