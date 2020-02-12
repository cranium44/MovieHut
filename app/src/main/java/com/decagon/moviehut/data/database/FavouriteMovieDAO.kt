package com.decagon.moviehut.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.moviehut.data.movieresponse.Movie

@Dao
interface FavouriteMovieDAO {

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getFavMovieById(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movies")
    fun getAllFavourites(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavouriteMovie(favouriteMovie: Movie)

    @Delete
    fun deleteFavouriteMovie(favouriteMovie: Movie)
}