package com.decagon.moviehut.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouriteMovieDAO {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavourites(): LiveData<List<FavouriteMovie>>

    @Insert
    fun addFavouriteMovie(favouriteMovie: FavouriteMovie)

    @Delete
    fun deleteFavouriteMovie(favouriteMovie: FavouriteMovie)
}