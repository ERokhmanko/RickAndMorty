package ru.netology.rickandmorty.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.rickandmorty.entity.NextPageLocationEntity

@Dao
interface NextPageLocationDao {
    @Query("SELECT next FROM NextPageLocationEntity ORDER BY id DESC LIMIT 1 ")
    suspend fun getLast(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nextPageLocationEntity: NextPageLocationEntity)
}
