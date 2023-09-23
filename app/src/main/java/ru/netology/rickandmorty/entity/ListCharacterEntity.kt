package ru.netology.rickandmorty.entity

import androidx.room.Embedded
import androidx.room.Entity
import ru.netology.rickandmorty.dto.Character
import ru.netology.rickandmorty.dto.InfoCharacter
import ru.netology.rickandmorty.dto.ListCharacter
import ru.netology.rickandmorty.dto.Location
import ru.netology.rickandmorty.dto.Origin

@Entity
data class ListCharacterEntity(
    @Embedded
    val info: InfoEmbeddable,
    @Embedded
    val results: List<CharacterEmbeddable>
) {
    fun toDto() = ListCharacter(info.toDto(), results.map { it.toDto() })

    companion object {
        fun fromDto(dto: ListCharacter) =
            ListCharacterEntity(InfoEmbeddable.fromDto(dto.info),
                dto.results.map {
                    CharacterEmbeddable.fromDto(it)
                })

    }
}

data class InfoEmbeddable(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Int?
) {
    fun toDto() = InfoCharacter(count, pages, next, prev)

    companion object {
        fun fromDto(dto: InfoCharacter) = InfoEmbeddable(dto.count, dto.pages, dto.next, dto.prev)
    }
}

data class CharacterEmbeddable(
    val id: Int, //The id of the character.
    val name: String, //The name of the character.
    val status: String, //The status of the character ('Alive', 'Dead' or 'unknown').
    val species: String, //The species of the character.
    val type: String, //The type or subspecies of the character.
    val gender: String, //The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
    @Embedded
    val origin: OriginEmbeddable, //Name and link to the character's origin location.
    @Embedded
    val location: LocationEmbeddable, //Name and link to the character's last known location endpoint.
    val image: String, //Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.
    val episode: List<String>, //List of episodes in which this character appeared.
    val url: String, //Link to the character's own URL endpoint.
    val created: String //Time at which the character was created in the database.
) {
    fun toDto() = Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toDto(),
        location = location.toDto(),
        image = image,
        episode = episode,
        url = url,
        created = created
    )

    companion object {
        fun fromDto(dto: Character) = CharacterEmbeddable(
            id = dto.id,
            name = dto.name,
            status = dto.status,
            species = dto.species,
            type = dto.type,
            gender = dto.gender,
            origin = OriginEmbeddable.fromDto(dto.origin),
            location = LocationEmbeddable.fromDto(dto.location),
            image = dto.image,
            episode = dto.episode,
            url = dto.url,
            created = dto.created
        )
    }

}

data class OriginEmbeddable(
    val name: String,
    val url: String
) {
    fun toDto() = Origin(name, url)

    companion object {
        fun fromDto(dto: Origin) = OriginEmbeddable(dto.name, dto.url)
        //TODO проверить может ли приходить null
    }
}


data class LocationEmbeddable(
    val name: String,
    val url: String
) {
    fun toDto() = Location(name, url)

    companion object {
        fun fromDto(dto: Location) = LocationEmbeddable(dto.name, dto.url)
        //TODO проверить может ли приходить null
    }

}