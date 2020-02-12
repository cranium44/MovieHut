package com.decagon.moviehut.controllers.repositories

import android.app.Application
import android.content.Context
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository(var application: Application) {
    lateinit var context: Context
    private var database = MovieDatabase.getInstance(application.applicationContext)

    fun getDataFromDatabase() = database.movieDao().getAllMovies()

    fun getFavourites() =
        database.movieDao().getFavouriteMovie(isFavourite = true)

    suspend fun saveFavouriteMovie(favouriteMovie: Movie) {
        withContext(Dispatchers.IO) {
            database.movieDao().addFavouriteMovie(favouriteMovie)
        }
    }


    suspend fun deleteFavouriteMovie(favouriteMovie: Movie) {
        withContext(Dispatchers.IO) {
            database.movieDao().deleteFavouriteMovie(favouriteMovie)
        }
    }
}