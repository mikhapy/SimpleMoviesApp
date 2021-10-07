package com.mikha.moviesapp.data.network.responses

import com.mikha.moviesapp.domain.model.Movie

data class MoviesResponse(
    var results: ArrayList<Movie>
)
