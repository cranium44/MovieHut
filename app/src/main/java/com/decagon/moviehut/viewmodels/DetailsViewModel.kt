package com.decagon.moviehut.viewmodels

import androidx.lifecycle.ViewModel
import com.decagon.moviehut.controllers.NetworkRepository
import com.decagon.moviehut.data.movieresponse.Movie

class DetailsViewModel : ViewModel() {
    lateinit var movie: Movie
    private val repository = NetworkRepository

//    fun getMovies(): List<Movie>{
//
//    }
}
