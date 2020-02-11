package com.decagon.moviehut.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.moviehut.controllers.DatabaseRepository
import com.decagon.moviehut.controllers.NetworkRepository
import com.decagon.moviehut.data.database.FavouriteMovie
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    lateinit var movie: Movie
    private val repository = NetworkRepository
    private val dbRepository = DatabaseRepository

    private val viewModelJob = Job()
    private var viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _favouriteMovies = MutableLiveData<List<FavouriteMovie>>()
    val favouriteMovies
        get() = _favouriteMovies


    init {
        viewModelScope.launch {
            _favouriteMovies.value = dbRepository.getDataFromDatabase().value
        }
    }
}
