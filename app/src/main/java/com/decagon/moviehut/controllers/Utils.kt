package com.decagon.moviehut.controllers

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import com.decagon.moviehut.data.database.FavouriteMovie
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

    fun convertToFavourite(movie: Movie): FavouriteMovie{
        return FavouriteMovie(
            movie.id.toLong(),
            movie.popularity,
            movie.title,
            movie.overview,
            movie.voteAverage,
            movie.releaseDate,
            movie.posterPath,
            movie.backdropPath,
            movie.isFavourite
        )
    }
}