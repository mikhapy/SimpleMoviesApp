package com.mikha.moviesapp.data.network

import com.mikha.moviesapp.BuildConfig
import com.mikha.moviesapp.data.network.responses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularMovies() : Response<MoviesResponse>

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }
}