package com.decagon.moviehut.controllers

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import java.io.FileOutputStream


object Utils {

    fun saveImage(context: Context, b: Bitmap, imageName: String?) {
        val foStream: FileOutputStream
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