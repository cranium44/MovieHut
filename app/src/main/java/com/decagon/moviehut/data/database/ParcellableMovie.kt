package com.decagon.moviehut.data.database

import android.os.Parcelable
import com.decagon.moviehut.data.movieresponse.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
class ParcellableMovie(
    override var adult: Boolean,
    override var backdropPath: String,
    override var id: Int,
    override var originalLanguage: String,
    override var  originalTitle: String,
    override var overview: String,
    override var popularity: Double,
    override var posterPath: String,
    override var releaseDate: String,
    override var title: String,
    override var video: Boolean,
    override var  voteAverage: Double,
    override var voteCount: Int,
    override var isFavourite: Boolean
) : Movie(
    adult,
    backdropPath,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount,
    isFavourite
), Parcelable {
}