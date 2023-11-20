package ru.netology.rickandmorty.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.rickandmorty.entity.EpisodeEntity

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM EpisodeEntity")
    fun getPagingSourceEpisode(): PagingSource<Int, EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(episodeEntity: List<EpisodeEntity>)
}