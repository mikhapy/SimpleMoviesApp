package com.mikha.moviesapp.data.repository

import android.accounts.NetworkErrorException
import com.mikha.moviesapp.data.local.ApplicationDatabase
import com.mikha.moviesapp.data.local.MovieDao
import com.mikha.moviesapp.data.network.MovieRemoteDataSource
import com.mikha.moviesapp.data.network.Resource
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.domain.repository.MovieRepository
import com.mikha.moviesapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MovieRepositoryImplTest: BaseUnitTest() {

//    private lateinit var movieRepository: MovieRepository
    private val movieRemoteDataSource: MovieRemoteDataSource = mock()
    private val localDataSource: ApplicationDatabase = mock()
    private val fakeMovieDao = FakeMovieDao()
    private val movieRepository = MovieRepositoryImpl(movieRemoteDataSource, localDataSource)

    val movieList = listOf<Movie>(
        Movie(false,"",1,"es","First","",20.00,"", "","First", false,3.0F,15),
        Movie(false,"",2,"es","Second","",10.00,"", "","Second", false,3.0F,15),
    )

    @Before
    fun setUp(){
        println("setup")
    }

//    @ExperimentalCoroutinesApi
//    @Test
//    fun getMoviesFromAPICallsRemoteDataSource(): Unit = runBlocking  {
//        mockNetworkSuccess()
//        val list =     movieRepository.getMovies().toList()
//        println("list = $list")
//        verify(movieRemoteDataSource, times(1)).getMovies()
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun getMoviesFromAPISaveDataInDatabase(): Unit = runBlocking  {
//        mockNetworkSuccess()
//        val list =  movieRepository.getMovies().toList()
//        assert(fakeMovieDao.savedMovies.size == movieList.size)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun getMoviesFromAPIThrowsException(): Unit = runBlocking{
//        mockNetworkFailed()
//        val exception = kotlin.runCatching {
//            val list =  movieRepository.getMovies().toList()
//        }.exceptionOrNull()
//        assertNotNull(exception)
//    }

    private suspend fun mockNetworkSuccess() {
        whenever(movieRemoteDataSource.getMovies()).thenReturn(
            Resource.success(movieList)
        )
        whenever(localDataSource.movieDao).thenReturn(fakeMovieDao)
    }

    private suspend fun mockNetworkFailed() {
        whenever(movieRemoteDataSource.getMovies()).thenReturn(
            Resource.error("Something wrong")
        )
        whenever(localDataSource.movieDao).thenReturn(fakeMovieDao)
    }
}

