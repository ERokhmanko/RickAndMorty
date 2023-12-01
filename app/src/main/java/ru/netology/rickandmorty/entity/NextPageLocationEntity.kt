package ru.netology.rickandmorty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NextPageLocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val next: String?
) {
    companion object {
        fun fromDto(nextPage: String?) =
            NextPageLocationEntity(next = nextPage)
    }
}
