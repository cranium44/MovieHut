package com.decagon.moviehut.data.movieresponse


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "movies")
open class Movie(
    open var adult: Boolean = false,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    open var backdropPath: String = "",

    @PrimaryKey(autoGenerate = false)
    open var id: Int = 0,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    open var originalLanguage: String = "",

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    open var originalTitle: String = "",
    open var overview: String = "",
    open var popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    open var posterPath: String = "",

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    open var releaseDate: String = "",
    open var title: String = "",
    open var video: Boolean = false,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    open var voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    open var voteCount: Int = 0,
    @Transient
    open var isFavourite: Boolean = false
)