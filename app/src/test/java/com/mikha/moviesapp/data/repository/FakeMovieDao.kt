package com.mikha.moviesapp.data.repository

import com.mikha.moviesapp.data.local.MovieDao
import com.mikha.moviesapp.domain.model.Movie

class FakeMovieDao: MovieDao {

    private val movieList = listOf<Movie>(
        Movie(false,"",1,"es","First","",20.00,"", "","First", false,3.0F,15),
        Movie(false,"",2,"es","Second","",10.00,"", "","Second", false,3.0F,15),
    )

    var savedMovies = arrayListOf<Movie>()
    override fun insertAll(movies: List<Movie>) {
        savedMovies.addAll(movies)
    }

    override fun getAllMovies(): List<Movie> {
        return movieList
    }
}