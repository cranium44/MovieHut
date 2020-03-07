package com.decagon.moviehut.controllers.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.decagon.moviehut.data.MovieDatabaseAPI
import com.decagon.moviehut.data.MovieDatabaseGenreAPI
import com.decagon.moviehut.data.VideoApi
import com.decagon.moviehut.data.database.MovieDatabase
import com.decagon.moviehut.data.genre.Genre
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(var application: Application) {
    private val TAG = "Network Repository"
    private val movieDatabaseAPI = MovieDatabaseAPI()
    private val genreAPI = MovieDatabaseGenreAPI()
    private val videoApi = VideoApi()

    var apiCalled = false

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
        apiCalled = true
        if(isNetworkConnected()){
            withContext(Dispatchers.IO) {
                data = getMovies()!!
                database.movieDao().addMovies(*data.toTypedArray())
            }
        }
    }

    suspend fun getTrailerUrls(movieId: Int):String{
        var url = ""
        if (isNetworkConnected()){
            withContext(Dispatchers.IO){
                try {
                    Log.d("REPOSITORY", movieId.toString())
                    val data = videoApi.getVideoTrailersAsync(movieId, URLRepository.API_KEY).await().results
                    val you = data.filter { result ->  result.site == "YouTube"}
                    url = you[0].key
                    Log.d("DATA TRAILER", url)
                }catch (t: Throwable){
                    Log.e("URL ERROR", t.message.toString())
                }
            }
        }
        return url
    }

    private fun isNetworkConnected(): Boolean{
        val connectivityManager = application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

}