package com.decagon.moviehut.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.decagon.moviehut.controllers.DatabaseRepository
import com.decagon.moviehut.data.database.FavouriteMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    //private val repository = NetworkRepository
    private val dbRepository = DatabaseRepository
    private val viewModelJob = Job()
    private var viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        dbRepository.context = application.applicationContext
    }

    fun saveFavourite(favouriteMovie: FavouriteMovie){
        viewModelScope.launch {
            dbRepository.saveFavouriteMovie(favouriteMovie)
        }
    }

    fun deleteFavourite(favouriteMovie: FavouriteMovie){
        viewModelScope.launch {
            dbRepository.deleteFavouriteMovie(favouriteMovie)
        }
    }
}
