package com.decagon.moviehut.controllers.repositories

interface URLRepository {
    companion object{
        const val API_BASE_URL = "https://api.themoviedb.org/3/discover/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        const val GENRE_BASE_URL = "https://api.themoviedb.org/3/genre/"
        const val VIDEO_BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val API_KEY = "28dda9f76d76f128b47831768bc9a103"
        const val YOUTUBE_API_KEY = "AIzaSyB5LLuT8D597fEOpwQm1eQKfw1c_MFosTQ"
    }
}