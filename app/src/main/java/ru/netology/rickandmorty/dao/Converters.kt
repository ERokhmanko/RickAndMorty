package ru.netology.rickandmorty.dao

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<String?>?): String? = list?.joinToString(",")

    @TypeConverter
    fun toList(data: String?): List<String?>? =
        if (data?.isBlank() == true) emptyList() else data?.split(",")?.map { it }
            ?.toList()

}