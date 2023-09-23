package ru.netology.rickandmorty.entity

import androidx.room.Embedded
import ru.netology.rickandmorty.dto.InfoLocation
import ru.netology.rickandmorty.dto.ListLocation
import ru.netology.rickandmorty.dto.Location

data class ListLocationEntity(
    @Embedded
    val info: InfoLocationEmbeddable,
    @Embedded
    val results: List<LocationEmbeddable>
) {
    fun toDto() = ListLocation(info.toDto(), results.map { it.toDto() })

    companion object {
        fun fromDto(dto: ListLocation) =
            ListLocationEntity(InfoLocationEmbeddable.fromDto(dto.info),
                dto.results.map {
                    LocationEmbeddable.fromDto(it)
                })
    }
}

data class InfoLocationEmbeddable(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Int?
) {
    fun toDto() = InfoLocation(count, pages, next, prev)

    companion object {
        fun fromDto(dto: InfoLocation) =
            InfoLocationEmbeddable(dto.count, dto.pages, dto.next, dto.prev)
    }
}

data class LocationEmbeddable(
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
        fun fromDto(dto: Location) = LocationEmbeddable(
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