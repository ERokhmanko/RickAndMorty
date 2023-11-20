package ru.netology.rickandmorty.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.rickandmorty.entity.NextPageEpisodeEntity

@Dao
interface NextPageEpisodeDao {

    @Query("SELECT next FROM NextPageEpisodeEntity ORDER BY id DESC LIMIT 1")
    suspend fun getLast(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nextPageEpisodeEntity: NextPageEpisodeEntity)
}