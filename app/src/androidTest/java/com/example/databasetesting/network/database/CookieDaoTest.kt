package com.example.databasetesting.network.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.databasetesting.getOrAwaitValue
import com.example.databasetesting.models.Cookie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Rule

// for assertions on Java 8 types (Streams and java.util.Optional)


@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class CookieDaoTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CookieDatabase
    private lateinit var dao:CookieDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CookieDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.currentCookieDao()

    }

    @Test
    fun insertCookieTest() = runTest(UnconfinedTestDispatcher()){
        val cookie = Cookie(2,"testcookie")
        dao.insert(cookie)
        val listOfCookie = dao.getAllCookiesFlow().getOrAwaitValue()

        assertThat(listOfCookie).contains(cookie)

    }

    @After
    fun tearDown(){
        database.close()
    }

}