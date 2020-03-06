package com.decagon.moviehut.data.video


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    var id: Int,
    var results: List<Result>
)