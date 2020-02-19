package com.decagon.moviehut.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.moviehut.data.movieresponse.Movie

@Dao
interface FavouriteMovieDAO {

    @Query("SELECT * FROM movies WHERE is_favourite = :isFavourite")
    fun getFavouriteMovie(isFavourite: Boolean): LiveData<List<Movie>>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getAllMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavouriteMovie(favouriteMovie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(vararg  movie: Movie)

    @Delete
    fun deleteFavouriteMovie(favouriteMovie: Movie)
}