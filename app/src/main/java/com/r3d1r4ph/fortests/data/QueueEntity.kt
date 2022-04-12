package com.r3d1r4ph.fortests.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queue")
data class QueueEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val tillsCount: Int,
    val customersTime: String,
    val minTime: Int
)
