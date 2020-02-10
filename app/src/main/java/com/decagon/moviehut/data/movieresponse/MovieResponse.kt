package com.decagon.moviehut.data.movieresponse


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    var page: Int,
    var results: List<Result>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)