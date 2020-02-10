package com.decagon.moviehut.data.database

class FavouriteMovie(
    val id: Long,
    val popularity: Double,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val genres: ArrayList<Genre>,
    var isFavorite: Boolean = false
)