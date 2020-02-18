package com.decagon.moviehut.controllers.repositories

import android.app.Application
import androidx.room.Room
import com.decagon.moviehut.data.database.FavouriteMovieDAO
import com.decagon.moviehut.data.database.MovieDatabase
import org.junit.Assert.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi

class DatabaseRepositoryTest {

    private lateinit var application: Application
    private lateinit var movieDAO: FavouriteMovieDAO
    private lateinit var db: MovieDatabase
    private val mainThreadSurrogate by lazy { newSingleThreadContext("UI thread") }
    private lateinit var SUT: DatabaseRepository




    @Test
    fun getDataFromDatabase() {
        runBlocking {
            launch(Dispatchers.Main) {
                val data = SUT.getDataFromDatabase()
                assertNotSame(data.value?.size, 0)
            }
        }
    }

    @Test
    fun getFavourites() {
    }

    @Test
    fun saveFavouriteMovie() {
    }

    @Test
    fun deleteFavouriteMovie() {
    }
}