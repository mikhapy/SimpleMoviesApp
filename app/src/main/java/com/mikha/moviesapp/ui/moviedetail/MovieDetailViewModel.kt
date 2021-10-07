package com.mikha.moviesapp.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikha.moviesapp.domain.model.Movie

class MovieDetailViewModel: ViewModel() {

    private var _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
    get() = _movie

    fun setMovie(movie: Movie){
        _movie.postValue(movie)
    }

}