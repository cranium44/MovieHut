package com.decagon.moviehut.viewmodels

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class DetailsViewModelTest {


    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var SUT : DetailsViewModel

    @Before
    fun setUp() {
        SUT = DetailsViewModel(ApplicationProvider.getApplicationContext())
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        mainThreadSurrogate.close()
    }

    @Test
    fun `saveFavourite function is triggered`() {
        runBlocking {
            launch(Dispatchers.Main) {
                //Given fresh movie object
                val movie = Movie()

                //When save favourite function is called
                SUT.saveFavourite(movie)

                //Then saveMovieTriggered
                assertThat(SUT.saveMovieTriggered, `is`(true))
            }
        }
    }

    @Test
    fun `deleteFavourite function is triggered`() {
        runBlocking {
            launch(Dispatchers.Main){
                //Given fresh movie
                val movie = Movie()

                //When deleteFavourite is called
                SUT.deleteFavourite(movie)

                //Then deleteMovieTriggered is true
                assertThat(SUT.deleteMovieTriggered, `is`(true))
            }
        }
    }
}