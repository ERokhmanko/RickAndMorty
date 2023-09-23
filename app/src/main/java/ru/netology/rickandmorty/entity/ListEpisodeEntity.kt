package ru.netology.rickandmorty.entity

import androidx.room.Embedded
import ru.netology.rickandmorty.dto.Episode
import ru.netology.rickandmorty.dto.InfoEpisode
import ru.netology.rickandmorty.dto.ListEpisode

data class ListEpisodeEntity(
    @Embedded
    val info: InfoEpisodeEmbedded,
    @Embedded
    val results: List<EpisodeEmbedded>
) {
    fun toDto() = ListEpisode(info.toDto(), results.map { it.toDto() })

    companion object {
        fun fromDto(dto: ListEpisode) =
            ListEpisodeEntity(InfoEpisodeEmbedded.fromDto(dto.info), dto.results.map {
                EpisodeEmbedded.fromDto(it)
            })
    }
}

data class InfoEpisodeEmbedded(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Int?
) {
    fun toDto() = InfoEpisode(count, pages, next, prev)

    companion object {
        fun fromDto(dto: InfoEpisode) =
            InfoEpisodeEmbedded(dto.count, dto.pages, dto.next, dto.prev)
    }
}

data class EpisodeEmbedded(
    val id: Int, //	The id of the episode.
    val name: String, //	The name of the episode.
    val airDate: String, //	The air date of the episode.
    val episode: String, //	The code of the episode.
    val characters: List<String>, // (urls)	List of characters who have been seen in the episode.
    val url: String, // (url)	Link to the episode's own endpoint.
    val created: String, //	Time at which the episode was created in the database.
) {
    fun toDto() = Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters,
        url = url,
        created = created
    )

    companion object {
        fun fromDto(dto: Episode) = EpisodeEmbedded(
            id = dto.id,
            name = dto.name,
            airDate = dto.airDate,
            episode = dto.episode,
            characters = dto.characters,
            url = dto.url,
            created = dto.created
        )
    }
}
