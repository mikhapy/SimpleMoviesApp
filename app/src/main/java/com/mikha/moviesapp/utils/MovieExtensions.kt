package com.mikha.moviesapp.utils

import com.mikha.moviesapp.domain.model.Movie

fun Movie.averageRateAsString(): String {
    return this.voteAverage.toString()
}