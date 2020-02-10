package com.decagon.moviehut.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.moviehut.controllers.MoviesAdapter
import com.decagon.moviehut.controllers.NetworkRepository
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = NetworkRepository
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _allMovies = MutableLiveData<List<Movie>>()
        val allMovies
        get() = _allMovies

    init {
        viewModelScope.launch {
            _allMovies.value = repository.getMovies()
        }
    }
}
