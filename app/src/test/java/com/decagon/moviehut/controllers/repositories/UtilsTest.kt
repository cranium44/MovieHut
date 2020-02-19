package com.decagon.moviehut.controllers.repositories

import com.decagon.moviehut.controllers.Utils
import com.decagon.moviehut.data.database.ParcellableMovie
import com.decagon.moviehut.data.movieresponse.Movie
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class UtilsTest {

    lateinit var movie: Movie
    lateinit var SUT : Utils

    @Before
    fun setUp() {
        movie = Movie()
        SUT = Utils
    }

    @Test
    fun convertToParcellable_converts_and_returns_parcellable() {
        assertThat(
            Utils.convertToParcellable(
                movie
            ), Matchers.isA(ParcellableMovie::class.java))
    }


    @Test
    fun test_convertDate_Returns_formatted_date() {
        var res = Utils.convertDate("2019-2-23")
        assertEquals("February 23, 2019", res)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun test_convertDate_throws_exception_when_wrong_format_passed(){
        Utils.convertDate("2019/2/23")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun test_convertDate_throws_exception_when_passed_empty_String(){
        Utils.convertDate("")
    }
}