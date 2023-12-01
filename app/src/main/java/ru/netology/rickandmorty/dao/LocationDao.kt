package ru.netology.rickandmorty.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.rickandmorty.entity.LocationEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM LocationEntity")
    fun getPagingSourceLocation(): PagingSource<Int, LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locationEntity: List<LocationEntity>)
}