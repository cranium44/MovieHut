package com.decagon.moviehut.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide.init
import com.decagon.moviehut.controllers.repositories.DatabaseRepository
import com.decagon.moviehut.controllers.repositories.NetworkRepository
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val dbRepository =
        DatabaseRepository(application)
    private val database = MovieDatabase.getInstance(application.applicationContext)
    private val repository =
        NetworkRepository(application)
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    //test variables
    var saveMovieFired = false
    var initFired = false

    var allMovies: LiveData<List<Movie>> = dbRepository.getDataFromDatabase()
    var favouriteMovies: LiveData<List<Movie>> = dbRepository.getFavourites()

    init {
        dbRepository.context = application.applicationContext
        viewModelScope.launch {
            repository.callApiAndSaveToDB(database)
            initFired = true
        }
    }

    fun saveFavourite(movie: Movie){
        saveMovieFired = true
        viewModelScope.launch {
            dbRepository.saveFavouriteMovie(movie)
        }
    }

}
