package com.decagon.moviehut.controllers.repositories

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.moviehut.data.database.FavouriteMovieDAO
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.movieresponse.Movie
import com.decagon.moviehut.getOrAwaitValue
import org.junit.Assert.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class DatabaseRepositoryTest {

    private var movie = Movie()
    private val mainThreadSurrogate by lazy { newSingleThreadContext("UI thread") }
    private lateinit var SUT: DatabaseRepository
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        SUT = DatabaseRepository(ApplicationProvider.getApplicationContext())
    }


    @After
    fun tearDown() {
        mainThreadSurrogate.close()
    }

    @Test
    fun `getDataFromDatabase returns the data`() {
        runBlocking {
            launch(Dispatchers.Main) {
                val data = SUT.getDataFromDatabase()
                assertNotSame(data.value?.size, 0)
            }
        }
    }

    @Test
    fun `getFavourites returns not null`() {
        runBlocking {
            launch(Dispatchers.Main) {
                //action
                val data = SUT.getFavourites()

                //assert
                assertThat(data.getOrAwaitValue().size, `is`(not(nullValue())))
            }
        }
    }

    @Test
    fun `saveFavouriteMovie is fired`() {
        runBlocking {
            launch(Dispatchers.Main){
                //Arrange fresh movie
                val testMovie = Movie(adult = true, title = "21 Bridges")

                //action save favourite
                SUT.saveFavouriteMovie(testMovie)

                //assert function is fired
                assertThat(SUT.testSaveMovieFired, `is`(true))

            }
        }
    }

    @Test
    fun `deleteFavouriteMovie is fired`() {
        runBlocking {
            launch(Dispatchers.Main){
                //Arrange fresh movie
                val testMovie = Movie(adult = true, title = "21 Bridges")

                //action save favourite
                SUT.deleteFavouriteMovie(testMovie)

                //assert function is fired
                assertThat(SUT.testDeleteMovieFired, `is`(true))

            }
        }
    }


}