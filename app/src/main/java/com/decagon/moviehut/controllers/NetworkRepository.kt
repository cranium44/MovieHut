package com.decagon.moviehut.controllers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.decagon.moviehut.data.MovieDatabaseAPI
import com.decagon.moviehut.data.MovieDatabaseGenreAPI
import com.decagon.moviehut.data.genre.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository {
    private val TAG = "Network Repository"
    private val movieDatabaseAPI = MovieDatabaseAPI()
    private val genreAPI = MovieDatabaseGenreAPI()

    suspend fun getGenres(): MutableLiveData<List<Genre>> {
        var data = MutableLiveData<List<Genre>>()
        withContext(Dispatchers.IO){
            try {
                data.value = genreAPI.getGenres(URLRepository.API_KEY).await().genres
            }catch (e: Error){
                data.value = null
                Log.e(TAG, e.message.toString())
            }
        }
        return data
    }


}