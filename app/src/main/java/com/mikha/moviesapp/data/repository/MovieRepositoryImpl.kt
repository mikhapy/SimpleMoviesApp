package com.mikha.moviesapp.data.repository

import android.accounts.NetworkErrorException
import com.mikha.moviesapp.data.local.ApplicationDatabase
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.data.network.MovieRemoteDataSource
import com.mikha.moviesapp.data.network.Resource
import com.mikha.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: ApplicationDatabase
): MovieRepository {

    override suspend fun getMovies(): Flow<List<Movie>> = flow {
        Timber.d("getMovies called")
        val dataFromDB = localDataSource.movieDao.getAllMovies()
        Timber.d("getMovies from localSource = ${dataFromDB.size}")
        emit(dataFromDB)
        val data = remoteDataSource.getMovies()
        if (data.status == Resource.Status.SUCCESS) {
            data.data?.let { localDataSource.movieDao.insertAll(it) }
            Timber.d("getMovies from remoteDataSource = ${data.data?.size}")
            emit(data.data ?: emptyList<Movie>())
        }else{
            Timber.d(data.message)
            throw NetworkErrorException(data.message)
        }
    }

    override suspend fun getMoviesFromLocal(): List<Movie> {
        return withContext(Dispatchers.IO){
            localDataSource.movieDao.getAllMovies()
        }
    }


}