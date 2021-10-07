package com.mikha.moviesapp.di

import com.mikha.moviesapp.data.repository.MovieRepositoryImpl
import com.mikha.moviesapp.domain.repository.MovieRepository
import com.mikha.moviesapp.domain.usecase.GetMovieListUseCase
import com.mikha.moviesapp.domain.usecase.GetMovieListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun getMovieList(getMovieListUseCaseImpl: GetMovieListUseCaseImpl): GetMovieListUseCase

    @Binds
    abstract fun getMovieRepository(getMovieRepository: MovieRepositoryImpl): MovieRepository

}