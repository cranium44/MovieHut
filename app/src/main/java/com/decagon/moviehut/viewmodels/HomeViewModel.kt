package com.decagon.moviehut.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.decagon.moviehut.controllers.DatabaseRepository
import com.decagon.moviehut.controllers.NetworkRepository
import com.decagon.moviehut.data.database.FavouriteMovie
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val dbRepository = DatabaseRepository
    private val database = MovieDatabase.getInstance(application.applicationContext)
    private val repository = NetworkRepository
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _allMovies = MutableLiveData<List<Movie>>()
        val allMovies
        get() = _allMovies

    private lateinit var _favouriteMovies : LiveData<List<FavouriteMovie>>
    val favouriteMovies
        get() = _favouriteMovies

    init {
        dbRepository.context = application.applicationContext
        viewModelScope.launch {
            _allMovies.value = repository.getMovies()
            _favouriteMovies = dbRepository.getDataFromDatabase(database)
        }
    }
}
