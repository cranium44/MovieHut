package com.decagon.moviehut.controllers.repositories

import android.app.Application
import android.util.Log
import com.decagon.moviehut.data.MovieDatabaseAPI
import com.decagon.moviehut.data.MovieDatabaseGenreAPI
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.genre.Genre
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(var application: Application) {
    private val TAG = "Network Repository"
    private val databaseRepository = DatabaseRepository(application)
    private val movieDatabaseAPI = MovieDatabaseAPI()
    private val genreAPI = MovieDatabaseGenreAPI()

    suspend fun getGenres(): List<Genre> {
        var data = ArrayList<Genre>()
        withContext(Dispatchers.IO){
            try {
                data = genreAPI.getGenres(
                    URLRepository.API_KEY
                ).await().genres as ArrayList<Genre>
            }catch (e: Error){

                Log.e(TAG, e.message.toString())
            }
        }
        return data
    }

    private suspend fun getMovies(): List<Movie>?{
        var data: List<Movie>? = null
        withContext(Dispatchers.IO){
            try {
                data = movieDatabaseAPI.getResponseAsync("popularity.desc",
                    URLRepository.API_KEY
                )
                    .await()
                    .movies
            }catch (t: Throwable){

                Log.e(TAG, t.message.toString())
            }
        }
        return data
    }

    suspend fun callApiAndSaveToDB(database: MovieDatabase){
        var data: List<Movie>
        withContext(Dispatchers.IO){
            data = getMovies()!!
            database.movieDao().addMovies(data)
        }
    }

}