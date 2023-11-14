package ru.netology.rickandmorty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.rickandmorty.dto.Episode
import ru.netology.rickandmorty.dto.InfoEpisode

@Entity
data class InfoEpisodeEntity(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Int?
) {
    fun toDto() = InfoEpisode(count, pages, next, prev)

    companion object {
        fun fromDto(dto: InfoEpisode) =
            InfoEpisodeEntity(dto.count, dto.pages, dto.next, dto.prev)
    }
}

@Entity
data class EpisodeEntity(
    @PrimaryKey
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
        fun fromDto(dto: Episode) = EpisodeEntity(
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
