package com.decagon.moviehut.controllers

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment
import android.util.Log
import com.decagon.moviehut.data.database.ParcellableMovie
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileOutputStream


object Utils {

    suspend fun saveImage(context: Context, b: Bitmap, imageName: String?): String {
        var foStream: FileOutputStream
        var filePath = ""
        withContext(Dispatchers.IO){
            try {
                val directory = Environment.getDataDirectory()
                foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE)
                b.compress(Bitmap.CompressFormat.PNG, 100, foStream)
                filePath = directory.absolutePath + imageName
                foStream.close()
            } catch (e: Exception) {
                Log.d("saveImage", "Exception 2, Something went wrong!")
                e.printStackTrace()
            }
        }
        return filePath
    }

    fun convertToParcellable(movie: Movie): ParcellableMovie{
        return ParcellableMovie(
            movie.adult,
            movie.backdropPath,
            movie.id,
            movie.originalLanguage,
            movie.originalTitle,
            movie.overview,
            movie.popularity,
            movie.posterPath,
            movie.releaseDate,
            movie.title,
            movie.video,
            movie.voteAverage,
            movie.voteCount,
            movie.isFavourite
        )
    }

    fun isNetworkConnected(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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