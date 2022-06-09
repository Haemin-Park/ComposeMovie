package com.example.movie.di

import com.example.movie.data.source.DefaultFilmRepository
import com.example.movie.data.source.FilmDataSource
import com.example.movie.data.source.FilmRepository
import com.example.movie.data.source.remote.FilmRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteFilmDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFilmRepository(
        @RemoteFilmDataSource remoteFilmDataSource: FilmDataSource
    ): FilmRepository {
        return DefaultFilmRepository(remoteFilmDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteFilmDataSource
    @Provides
    fun provideFilmRemoteDataSource(): FilmDataSource = FilmRemoteDataSource
}