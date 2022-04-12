package com.r3d1r4ph.fortests.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {
    private lateinit var appDatabase: AppDatabase
    private lateinit var queueDao: QueueDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        queueDao = appDatabase.getQueueDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun writeQueueAndReadInList() = runBlocking {
        val queue = QueueEntity(
            id = 0,
            tillsCount = 1,
            customersTime = "5",
            minTime = 5
        )

        queueDao.insert(queue)

        assertTrue(queueDao.getAll().contains(queue))
    }
}