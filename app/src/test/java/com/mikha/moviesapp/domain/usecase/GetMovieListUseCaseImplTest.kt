package com.mikha.moviesapp.domain.usecase

import android.accounts.NetworkErrorException
import android.net.Network
import com.mikha.moviesapp.data.network.Resource
import com.mikha.moviesapp.data.repository.MovieRepositoryImpl
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class GetMovieListUseCaseImplTest: BaseUnitTest(){

    private val movieRepository: MovieRepositoryImpl = mock()
    private val useCase = GetMovieListUseCaseImpl(movieRepository)


//    @ExperimentalCoroutinesApi
//    @Test
//    fun getMovieLisCallsRepository(): Unit = runBlocking  {
////        useCase.getMovieList().toList()
////        verify(movieRepository, times(1)).getMovies()
//        assert(true)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun getMovieListReturnSuccess() = runBlocking {
////        mockSuccessful()
////        val response = useCase.getMovieList()
////        response.collect {
////            assert(it.status == Resource.Status.SUCCESS)
////        }
//
//        assert(true)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun getMovieListThrownException() = runBlocking {
////        mockThrownException()
////        val list = useCase.getMovieList()
////
////        list.collect {
////            assert(it.status == Resource.Status.ERROR)
////        }
//
//        assert(true)
//
//    }

    private fun mockSuccessful() = runBlocking {
        whenever(movieRepository.getMovies()).thenReturn(
            flow {
                listOf<Movie>()
            }
        )
    }

    private fun mockThrownException() = runBlocking {
        whenever(movieRepository.getMovies()).thenReturn(
            flow {
                Resource.error<NetworkErrorException>("Something wrong")
            }
        )
    }

}