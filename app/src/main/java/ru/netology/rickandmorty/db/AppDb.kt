package ru.netology.rickandmorty.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.netology.rickandmorty.dao.CharacterDao
import ru.netology.rickandmorty.dao.Converters
import ru.netology.rickandmorty.dao.NextPageCharacterDao
import ru.netology.rickandmorty.entity.CharacterEntity
import ru.netology.rickandmorty.entity.NextPageCharacterEntity

@Database(
    entities = [CharacterEntity::class, NextPageCharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    abstract fun nextPageCharacterDao(): NextPageCharacterDao
}