package ru.netology.rickandmorty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NextPageCharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val next: String?,
) {
    companion object {
        fun fromDto(nextPage: String?) =
            NextPageCharacterEntity( next = nextPage)
    }
}

