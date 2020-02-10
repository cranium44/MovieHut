package com.decagon.moviehut.viewmodels

import androidx.lifecycle.ViewModel
import com.decagon.moviehut.controllers.NetworkRepository
import com.decagon.moviehut.data.movieresponse.Result

class DetailsViewModel : ViewModel() {
    lateinit var result: Result
    private val repository = NetworkRepository

//    fun getMovies(): List<Result>{
//
//    }
}
