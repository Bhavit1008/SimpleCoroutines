package com.example.simplecoroutines.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PracticeDao {
    @Insert
    suspend fun insert(data:Entity)

    @Query("select * from sample_data")
    suspend fun getAllValues(): List<Entity>
}