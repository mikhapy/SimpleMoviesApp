package com.mikha.moviesapp.data.network

import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.data.network.responses.MoviesResponse
import com.mikha.moviesapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import retrofit2.Response
import java.util.ArrayList

class MovieRemoteDataSourceTest: BaseUnitTest() {

//    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
    private val movieService: MovieService = mock()
    private val movies: ArrayList<Movie> = mock()
    private var movieRemoteDataSource = MovieRemoteDataSource(movieService)

    @Before
    fun setUp(){

    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMoviesFromService() = runBlockingTest {
        movieService.getPopularMovies()
        verify(movieService, times(1)).getPopularMovies()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMoviesReturnSuccess() = runBlockingTest {
        mockSuccessfulCase()
        assertEquals(Resource.success(movies), movieRemoteDataSource.getMovies())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMoviesReturnError() = runBlockingTest {
        mockFailureCase()
        assertEquals(Resource.error("Something was wrong", data = null), movieRemoteDataSource.getMovies())
    }

    private suspend fun mockFailureCase() {
        whenever(movieService.getPopularMovies()).thenThrow(RuntimeException("Something was wrong"))
    }

    private suspend fun mockSuccessfulCase() {
        val movieResponse = MoviesResponse(movies)
        whenever(movieService.getPopularMovies()).thenReturn(
            Response.success(movieResponse)
        )
    }

}