package com.decagon.moviehut.data.database

import androidx.room.TypeConverter
import com.decagon.moviehut.data.genre.Genre

class Converter {

    @TypeConverter
//    @JvmStatic
    fun genreToString(genre: Genre): String{
        return genre.toString()
    }

    @TypeConverter
    fun stringToGenre(string: String): Genre {
        val intermediate = string.split("\n")
        return Genre(intermediate[0].toInt(), intermediate[1])
    }
}