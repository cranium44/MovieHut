package com.decagon.moviehut.data

import com.decagon.moviehut.controllers.repositories.URLRepository
import com.decagon.moviehut.data.video.VideoResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApi {
    @GET("{id}/videos")
    fun getVideoTrailersAsync(
        @Path("id")
        id: Int,
        @Query("api_key")
        apiKey: String
    ): Deferred<VideoResponse>

    companion object{
        operator fun invoke(): VideoApi{
            val okHttpClient = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(URLRepository.VIDEO_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VideoApi::class.java)
        }
    }
}