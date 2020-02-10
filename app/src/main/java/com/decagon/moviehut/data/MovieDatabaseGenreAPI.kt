package com.decagon.moviehut.data

import com.decagon.moviehut.controllers.URLRepository
import com.decagon.moviehut.data.genre.GenreResponse
import kotlinx.coroutines.Deferred
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatabaseGenreAPI {

    @GET("movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String
    ): Deferred<GenreResponse>

    companion object{
        operator fun invoke(): MovieDatabaseGenreAPI{
            val okHttpClient = Builder().build()
            return Retrofit.Builder()
                .baseUrl(URLRepository.GENRE_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieDatabaseGenreAPI::class.java)
        }
    }
}