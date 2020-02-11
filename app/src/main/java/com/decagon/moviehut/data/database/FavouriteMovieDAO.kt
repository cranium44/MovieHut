package com.decagon.moviehut.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteMovieDAO {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavourites(): LiveData<List<FavouriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavouriteMovie(favouriteMovie: FavouriteMovie)

    @Delete
    fun deleteFavouriteMovie(favouriteMovie: FavouriteMovie)
}