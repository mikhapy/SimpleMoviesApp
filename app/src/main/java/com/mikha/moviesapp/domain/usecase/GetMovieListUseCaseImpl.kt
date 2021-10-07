package com.mikha.moviesapp.domain.usecase

import android.accounts.NetworkErrorException
import android.util.Log
import com.mikha.moviesapp.data.network.Resource
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class GetMovieListUseCaseImpl @Inject constructor(private val repository: MovieRepository): GetMovieListUseCase {
    override suspend fun getMovieList(): Flow<Resource<List<Movie>>> = flow {
        try{
             repository.getMovies().collect {
                 Timber.d("usecase = ${it.size}")
                emit(Resource.success(it))
            }
        }catch (e: NetworkErrorException){
            Timber.e(e)
            emit(Resource.error(e.message ?: "Something wrong", null))
        }catch (e: Exception){
            Timber.e(e)
            emit(Resource.error(e.message ?: "Something wrong", null))
        }
    }
}