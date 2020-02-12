package com.decagon.moviehut.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.decagon.moviehut.data.movieresponse.Movie


@Database(entities = [Movie::class], version = 7)
@TypeConverters(Converter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): FavouriteMovieDAO

    companion object{
        private var instance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, MovieDatabase::class.java, "moviedb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}