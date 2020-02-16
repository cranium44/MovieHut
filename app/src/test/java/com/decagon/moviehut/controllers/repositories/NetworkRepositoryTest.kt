package com.decagon.moviehut.controllers.repositories

import android.app.Application
import com.decagon.moviehut.data.MovieDatabaseAPI
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class NetworkRepositoryTest {

    //@MockK
    lateinit var application: Application
    @MockK
    lateinit var movieDatabaseAPI: MovieDatabaseAPI
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    lateinit var SUT : NetworkRepository

    @Before
    fun setUp() {
        application = Application()
        MockKAnnotations.init(this)
        SUT = NetworkRepository(application)
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
    }
}