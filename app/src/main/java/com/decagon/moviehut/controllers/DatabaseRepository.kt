package com.decagon.moviehut.controllers

import android.content.Context
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseRepository {
    lateinit var context: Context

    fun getDataFromDatabase(database: MovieDatabase) = database.movieDao().getAllMovies()

    suspend fun saveFavouriteMovie(favouriteMovie: Movie, database: MovieDatabase){
        withContext(Dispatchers.IO){
            database.movieDao().addFavouriteMovie(favouriteMovie)
        }
    }

    suspend fun deleteFavouriteMovie(favouriteMovie: Movie, database: MovieDatabase){
        withContext(Dispatchers.IO){
            database.movieDao().deleteFavouriteMovie(favouriteMovie)
        }
    }
}