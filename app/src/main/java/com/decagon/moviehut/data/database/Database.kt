package com.decagon.moviehut.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavouriteMovie::class], version = 1)
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