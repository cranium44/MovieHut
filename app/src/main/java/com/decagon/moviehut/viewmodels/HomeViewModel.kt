package com.decagon.moviehut.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.moviehut.controllers.MoviesAdapter
import com.decagon.moviehut.controllers.NetworkRepository
import com.decagon.moviehut.data.movieresponse.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val repository = NetworkRepository
    val viewModelJob = Job()
    val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var adapter = MoviesAdapter()

    private var _allMovies = MutableLiveData<List<Result>>()
        val allMovies : LiveData<List<Result>>
        get() = _allMovies

    init {
        viewModelScope.launch {
            _allMovies = repository.getMovies()
        }
    }

    fun getAdpter(): MoviesAdapter{
        return adapter
    }




}
