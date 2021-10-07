package com.mikha.moviesapp.data.network

import android.accounts.NetworkErrorException
import com.mikha.moviesapp.domain.model.Movie
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMovies(): Resource<List<Movie>> {
        Timber.d("getMovies() called")
        try {
            val result = movieService.getPopularMovies()
            Timber.d("result $result")
            if (result.isSuccessful) {
                result.body()?.let { it ->
                    return Resource.success(it.results)
                }
            }
            return Resource.error(result.code().toString())
        }catch (e: NetworkErrorException){
            return Resource.error("${e.message}")
        }catch (e: Exception){
            return Resource.error("${e.message}")
        }

    }
}