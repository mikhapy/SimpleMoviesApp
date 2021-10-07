package com.mikha.moviesapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mikha.moviesapp.domain.model.Movie
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var movieDao: MovieDao
    private lateinit var movieDatabase: ApplicationDatabase

    // Fake Data
    val movieList = listOf<Movie>(
        Movie(false,"",1,"es","First","",20.00,"", "","First", false,3.0F,15),
        Movie(false,"",2,"es","Second","",10.00,"", "","Second", false,3.0F,15),
    )

    val movieListSameID = listOf<Movie>(
        Movie(false,"",1,"es","First","",20.00,"", "","First", false,3.0F,15),
        Movie(false,"",1,"es","Second","",10.00,"", "","Second", false,3.0F,15),
    )

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        movieDatabase = Room.inMemoryDatabaseBuilder(context, ApplicationDatabase::class.java).build()
        movieDao = movieDatabase.movieDao
    }

    @After
    @Throws(IOException::class)
    fun closeDB(){
        movieDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun getAllMoviesReturnList() {
        movieDao.insertAll(movieList)
        val list = movieDao.getAllMovies()
        assert(list == movieList)
    }

    @Test
    @Throws(Exception::class)
    fun getAllMoviesReturnListOrderedByPopularity(){
        movieDao.insertAll(movieList)
        val list = movieDao.getAllMovies()
        assert(list.first().popularity > list.last().popularity)
    }

    @Test
    @Throws(Exception::class)
    fun insertAllMoviesIgnoreSameID(){
        movieDao.insertAll(movieListSameID)
        val list = movieDao.getAllMovies()
        assert(movieList != movieListSameID)
    }

}