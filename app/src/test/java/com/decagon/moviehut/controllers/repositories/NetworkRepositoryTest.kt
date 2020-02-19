package com.decagon.moviehut.controllers.repositories

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.moviehut.data.MovieDatabaseAPI
import com.decagon.moviehut.data.database.MovieDatabase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class NetworkRepositoryTest {

    //@MockK
    lateinit var application: Application
    @MockK
    lateinit var movieDatabaseAPI: MovieDatabaseAPI
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    lateinit var SUT : NetworkRepository
    private lateinit var db: MovieDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        application = ApplicationProvider.getApplicationContext()
        MockKAnnotations.init(this)
        SUT = NetworkRepository(application)
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MovieDatabase::class.java).build()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun getGenres(){
        runBlocking {
            launch(Dispatchers.Main) {
                val data = SUT.getGenres()
                assertNotSame(0, data.size)
            }
        }
    }

    @Test
    fun callApiAndSaveToDB() {
        runBlocking {
            launch {
                //when call function is called
                SUT.callApiAndSaveToDB(db)

                //then apiCalled is set to true
                assertThat(SUT.apiCalled, `is`(true))
            }
        }
    }
}