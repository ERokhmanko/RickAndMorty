package ru.netology.rickandmorty.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.netology.rickandmorty.dao.CharacterDao
import ru.netology.rickandmorty.dao.Converters
import ru.netology.rickandmorty.dao.EpisodeDao
import ru.netology.rickandmorty.dao.LocationDao
import ru.netology.rickandmorty.dao.NextPageCharacterDao
import ru.netology.rickandmorty.dao.NextPageEpisodeDao
import ru.netology.rickandmorty.dao.NextPageLocationDao
import ru.netology.rickandmorty.entity.CharacterEntity
import ru.netology.rickandmorty.entity.EpisodeEntity
import ru.netology.rickandmorty.entity.LocationEntity
import ru.netology.rickandmorty.entity.NextPageCharacterEntity
import ru.netology.rickandmorty.entity.NextPageEpisodeEntity
import ru.netology.rickandmorty.entity.NextPageLocationEntity

@Database(
    entities = [CharacterEntity::class, NextPageCharacterEntity::class,
        EpisodeEntity::class, NextPageEpisodeEntity::class,
        LocationEntity::class, NextPageLocationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun nextPageCharacterDao(): NextPageCharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun nextPageEpisodeDao(): NextPageEpisodeDao
    abstract fun locationDao() : LocationDao
    abstract fun nextPageLocationDao() : NextPageLocationDao
}