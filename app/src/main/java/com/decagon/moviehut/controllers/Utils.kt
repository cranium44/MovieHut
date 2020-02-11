package com.decagon.moviehut.controllers

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileOutputStream


object Utils {

    suspend fun saveImage(context: Context, b: Bitmap, imageName: String?) {
        var foStream: FileOutputStream
        withContext(Dispatchers.IO){
            try {
                foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE)
                b.compress(Bitmap.CompressFormat.PNG, 100, foStream)
                foStream.close()
            } catch (e: Exception) {
                Log.d("saveImage", "Exception 2, Something went wrong!")
                e.printStackTrace()
            }
        }
    }
}