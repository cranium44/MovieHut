package com.decagon.moviehut.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.decagon.moviehut.data.genre.Genre

@Entity(tableName = "favourite_movies")
data class FavouriteMovie(
    @PrimaryKey
    val id: Long,
    val popularity: Double,
    val title: String,
    val overview: String,
    val vote_average: Double,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    val genre: Genre,
    @ColumnInfo(name = "is_favourite")
    var isFavorite: Boolean = false
)