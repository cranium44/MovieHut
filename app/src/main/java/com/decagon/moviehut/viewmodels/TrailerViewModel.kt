package com.decagon.moviehut.viewmodels

import android.app.Application
import android.util.Log
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

    init {
        key=""
    }

    fun getKey(movieId: Int): String{
        var url = ""
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("VIEWMODEL", movieId.toString())
            url = networkRepository.getTrailerUrls(movieId)
        }
        Log.d("VIEWMODEL", url)
        return url
    }

}