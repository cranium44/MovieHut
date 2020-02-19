package com.decagon.moviehut.viewmodels

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProvider
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
class HomeViewModelTest {


    private lateinit var SUT : HomeViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        SUT = HomeViewModel(ApplicationProvider.getApplicationContext())
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        mainThreadSurrogate.close()
    }

    @Test
    fun `saveFavourite is triggered`() {
        runBlocking {

                //given fresh movie object
                val movie = Movie()

                //When saveFavorites is called
                SUT.saveFavourite(movie)

                //then saveFavourite is set to true
                assertThat(SUT.saveMovieFired, `is`(true))
        }
    }

    @Test
    fun `View model instantiation is fired`(){
        runBlocking {
            launch(Dispatchers.Main){
                //when viewmodel is fired

                //then initFired variable is set to true
                assertThat(SUT.initFired, `is`(true))
            }
        }
    }
}