package com.decagon.moviehut.models.database

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.decagon.moviehut.controllers.repositories.DatabaseRepository
import com.decagon.moviehut.data.database.FavouriteMovieDAO
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.movieresponse.Movie
import io.mockk.MockKAnnotations
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.moviehut.getOrAwaitValue
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.cyberneko.html.HTMLElements.VAR
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
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
class DataBaseTest {


    private var application: Application = mockk()
    private lateinit var db: MovieDatabase
    private lateinit var movieDAO: FavouriteMovieDAO
    private lateinit var movie: Movie

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        movie = Movie()
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MovieDatabase::class.java).build()
        movieDAO = db.movieDao()

        Dispatchers.setMain(mainThreadSurrogate)

    }

    @After
    fun tearDown() {
        db.close()
    }

    /**
     * Value is inserted into the database correctly
     */
    @Test
    @Throws(Exception::class)
    fun `database inserts properly`(){
        runBlocking {
            launch(Dispatchers.Main){
                //act
                movieDAO.addFavouriteMovie(movie)

                //assert
                val result = movieDAO.getAllMovies()
                assertThat(result.getOrAwaitValue(), not(nullValue()))
            }
        }
    }

    @Test
    fun `Database can save multiple items`(){

        runBlocking {
            launch(Dispatchers.Main) {
                //Arrange a fresh list of movies
                val data = arrayOf(
                    Movie(false, title = "21 bridges", overview = "jon champion"),
                    Movie(true, title = "bossman", isFavourite = true),
                    Movie(false, releaseDate = "2019-3-12")
                )

                //save the data to the database
                movieDAO.addMovies(*data)

                //Assert that the data has been added
                val result = movieDAO.getAllMovies()
                assertThat(result.getOrAwaitValue().size, `is`(not(0)))
            }
        }
    }

    @Test
    fun `Database can return favourite movie`(){
        runBlocking {
            launch(Dispatchers.Main) {
                //Arrange fresh data in database
                movieDAO.addFavouriteMovie(movie)

                //Act
                val result = movieDAO.getFavouriteMovie(false)

                //assert
                assertThat(result.getOrAwaitValue()[0].adult, `is`(false))
            }
        }
    }

    @Test
    fun `Database can delete correctly`(){
        runBlocking {
            launch(Dispatchers.Main) {
                //Arrange fresh data in database
                movieDAO.addFavouriteMovie(movie)

                //act
                movieDAO.deleteFavouriteMovie(movie)

                //assert
                val result = movieDAO.getAllMovies()
                assertThat(result.getOrAwaitValue().size, `is`(0))
            }
        }
    }
}