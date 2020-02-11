package com.decagon.moviehut.viewmodels

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import com.decagon.moviehut.controllers.DatabaseRepository
import com.decagon.moviehut.controllers.Utils
import com.decagon.moviehut.data.database.FavouriteMovie
import com.decagon.moviehut.data.database.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    //private val repository = NetworkRepository
    private val database = MovieDatabase.getInstance(application.applicationContext)
    private val dbRepository = DatabaseRepository
    private val utils = Utils
    private val viewModelJob = Job()
    private var viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        dbRepository.context = application.applicationContext
    }

    fun saveFavourite(favouriteMovie: FavouriteMovie){
        viewModelScope.launch {
            dbRepository.saveFavouriteMovie(favouriteMovie, database)
        }
    }

    fun deleteFavourite(favouriteMovie: FavouriteMovie){
        viewModelScope.launch {
            dbRepository.deleteFavouriteMovie(favouriteMovie, database)
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
