package ru.netology.rickandmorty.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.rickandmorty.dto.Character
import ru.netology.rickandmorty.dto.LocationCharacter
import ru.netology.rickandmorty.dto.Origin

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int, //The id of the character.
    val name: String, //The name of the character.
    val status: String, //The status of the character ('Alive', 'Dead' or 'unknown').
    val species: String, //The species of the character.
    val type: String, //The type or subspecies of the character.
    val gender: String, //The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
    @Embedded
    val origin: OriginEmbeddable, //Name and link to the character's origin location.
    @Embedded
    val location: LocationCharacterEmbeddable, //Name and link to the character's last known location endpoint.
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
        fun fromDto(dto: Character) = CharacterEntity(
            id = dto.id,
            name = dto.name,
            status = dto.status,
            species = dto.species,
            type = dto.type,
            gender = dto.gender,
            origin = OriginEmbeddable.fromDto(dto.origin),
            location = LocationCharacterEmbeddable.fromDto(dto.location),
            image = dto.image,
            episode = dto.episode,
            url = dto.url,
            created = dto.created
        )
    }

}

data class OriginEmbeddable(
    val nameOrigin: String,
    val urlOrigin: String
) {
    fun toDto() = Origin(nameOrigin, urlOrigin)

    companion object {
        fun fromDto(dto: Origin) = OriginEmbeddable(dto.name, dto.url)
    }
}


data class LocationCharacterEmbeddable(
    val nameLocation: String,
    val urlLocation: String
) {
    fun toDto() = LocationCharacter(nameLocation, urlLocation)

    companion object {
        fun fromDto(dto: LocationCharacter) = LocationCharacterEmbeddable(dto.name, dto.url)
    }

}