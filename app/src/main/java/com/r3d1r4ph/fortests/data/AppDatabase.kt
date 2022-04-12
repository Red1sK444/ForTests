package com.r3d1r4ph.fortests.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QueueEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getQueueDao(): QueueDao
}