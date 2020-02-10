package com.decagon.moviehut.controllers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.decagon.moviehut.data.MovieDatabaseAPI
import com.decagon.moviehut.data.MovieDatabaseGenreAPI
import com.decagon.moviehut.data.genre.Genre
import com.decagon.moviehut.data.movieresponse.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NetworkRepository {
    private val TAG = "Network Repository"
    private val movieDatabaseAPI = MovieDatabaseAPI()
    private val genreAPI = MovieDatabaseGenreAPI()

    suspend fun getGenres(): List<Genre> {
        var data = ArrayList<Genre>()
        withContext(Dispatchers.IO){
            try {
                data = genreAPI.getGenres(URLRepository.API_KEY).await().genres as ArrayList<Genre>
            }catch (e: Error){

                Log.e(TAG, e.message.toString())
            }
        }
        return data
    }

    suspend fun getMovies(): MutableLiveData<List<Result>>{
        var data = MutableLiveData<List<Result>>()
        withContext(Dispatchers.IO){
            try {
                data.value = movieDatabaseAPI.getResponse("popularity.desc", URLRepository.API_KEY)
                    .await()
                    .results
            }catch (t: Throwable){
                data.value = null
                Log.e(TAG, t.message.toString())
            }
        }
        return data
    }


}