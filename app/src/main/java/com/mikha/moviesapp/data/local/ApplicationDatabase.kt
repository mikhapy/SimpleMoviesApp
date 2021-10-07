package com.mikha.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mikha.moviesapp.domain.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        const val DB_NAME = "movie_app"
    }
}