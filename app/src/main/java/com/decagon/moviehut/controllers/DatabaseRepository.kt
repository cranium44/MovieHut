package com.decagon.moviehut.controllers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.decagon.moviehut.data.database.FavouriteMovie
import com.decagon.moviehut.data.database.MovieDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseRepository {
    lateinit var context: Context
    private val database = MovieDatabase.getInstance(context)

    suspend fun getDataFromDatabase(): MutableLiveData<List<FavouriteMovie>> {
        var data = MutableLiveData<List<FavouriteMovie>>()
        withContext(Dispatchers.IO){
            data = database.movieDao().getAllFavourites() as MutableLiveData<List<FavouriteMovie>>
        }
        return data
    }
}