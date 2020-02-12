package com.decagon.moviehut.viewmodels

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import com.decagon.moviehut.controllers.repositories.DatabaseRepository
import com.decagon.moviehut.controllers.Utils
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    //private val repository = NetworkRepository
    private val database = MovieDatabase.getInstance(application.applicationContext)
    private val dbRepository =
        DatabaseRepository(application)
    private val utils = Utils
    private val viewModelJob = Job()
    private var viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        dbRepository.context = application.applicationContext
    }

    fun saveFavourite(favouriteMovie: Movie){
        viewModelScope.launch {
            dbRepository.saveFavouriteMovie(favouriteMovie)
        }
    }

    fun deleteFavourite(favouriteMovie: Movie){
        viewModelScope.launch {
            dbRepository.deleteFavouriteMovie(favouriteMovie)
        }
    }

    fun saveimage(context: Context, bitmap: Bitmap, imageName: String): String{
        var filePath = ""
        viewModelScope.launch {
            filePath = utils.saveImage(context, bitmap, imageName)
        }
        return filePath
    }
}
