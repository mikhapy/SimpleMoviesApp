package com.mikha.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.mikha.moviesapp.BuildConfig
import com.mikha.moviesapp.data.local.ApplicationDatabase
import com.mikha.moviesapp.data.network.MovieRemoteDataSource
import com.mikha.moviesapp.data.network.MovieService
import com.mikha.moviesapp.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
        .build()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(service: MovieService): MovieRemoteDataSource = MovieRemoteDataSource(service)

    @Singleton
    @Provides
    fun provideMovieRepository(dataSource: MovieRemoteDataSource, localDataSource: ApplicationDatabase): MovieRepositoryImpl = MovieRepositoryImpl(dataSource, localDataSource)

    @Singleton
    @Provides
    fun provideApplicationDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room.databaseBuilder(context, ApplicationDatabase::class.java, ApplicationDatabase.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: ApplicationDatabase) = db.movieDao

}