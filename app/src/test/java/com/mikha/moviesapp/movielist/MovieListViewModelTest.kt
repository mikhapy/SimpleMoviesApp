package com.mikha.moviesapp.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.mikha.moviesapp.data.repository.MovieRepositoryImpl
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.domain.usecase.GetMovieListUseCase
import com.mikha.moviesapp.ui.movielist.MovieListViewModel
import com.mikha.moviesapp.utils.BaseUnitTest
import com.mikha.moviesapp.utils.MainCoroutineScopeRule
import com.mikha.moviesapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MovieListViewModelTest: BaseUnitTest() {

    private lateinit var viewModel: MovieListViewModel


    private val repository: MovieRepositoryImpl = mock()
    private val useCase: GetMovieListUseCase = mock()

    private val movies = mock<List<Movie>>()

    @Before
    fun setUp(){
//        mockSuccessfulTest()
        viewModel = MovieListViewModel(useCase)
    }




    @ExperimentalCoroutinesApi
    @Test
    fun getMoviesFromRepository() = runBlockingTest {
        viewModel.getMoviesFromRepository(false)
    }
//    @Test
//    fun receiveNullMovieFromMovieListRepositoryFirstTime() = runBlockingTest {
//        assertEquals(null, viewModel.movies.getValueForTest())
//    }
//
//    private fun mockSuccessfulTest(){
//        runBlockingTest {
//            whenever().thenReturn(
//                liveData {
//                    movies
//                }
//            )
//        }
//    }

}