package com.decagon.moviehut.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import com.decagon.moviehut.controllers.repositories.NetworkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TrailerViewModel(application: Application): AndroidViewModel(application) {
    private val networkRepository = NetworkRepository(application)
    lateinit var key: String

    //coroutine
    private val job = Job()
    private val viewModelScope = CoroutineScope(job + Dispatchers.Main)

    fun getKey(movieId: Int): String{
        viewModelScope.launch {
            key = networkRepository.getTrailerUrls(movieId)
        }
        return key
    }
}