package com.decagon.moviehut.data

import com.decagon.moviehut.controllers.URLRepository
import com.decagon.moviehut.data.movieresponse.MovieResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatabaseAPI {
    @GET("movie")
    fun getResponse(
        @Query("sort_by") sortBy: String,
        @Query("api_key") apiKey: String
    ): Deferred<MovieResponse>

    companion object{
        operator fun invoke(): MovieDatabaseAPI{
            val okHttpClient = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(URLRepository.API_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieDatabaseAPI::class.java)
        }
    }

//    @GET("genre")
//    fun getGenre()
}