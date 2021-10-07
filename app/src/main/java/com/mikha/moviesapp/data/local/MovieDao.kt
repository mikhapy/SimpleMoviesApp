package com.mikha.moviesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mikha.moviesapp.domain.model.Movie

@Dao
interface MovieDao {

    @Query("select * from movies order by popularity DESC")
    fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<Movie>)
}