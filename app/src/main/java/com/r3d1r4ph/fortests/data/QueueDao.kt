package com.r3d1r4ph.fortests.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QueueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(queueEntity: QueueEntity)

    @Query("SELECT * FROM queue")
    suspend fun getAll(): List<QueueEntity>
}