package com.mikha.moviesapp.domain.repository

import com.mikha.moviesapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<List<Movie>>

    suspend fun getMoviesFromLocal(): List<Movie>
}