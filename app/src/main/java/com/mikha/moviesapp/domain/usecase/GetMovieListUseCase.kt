package com.mikha.moviesapp.domain.usecase

import com.mikha.moviesapp.data.network.Resource
import com.mikha.moviesapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMovieListUseCase {

    suspend fun getMovieList(): Flow<Resource<List<Movie>>>
}