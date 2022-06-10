package com.example.movie.di

import com.example.movie.data.source.ColorDataSource
import com.example.movie.data.source.FilmDataSource
import com.example.movie.data.source.PeopleDataSource
import com.example.movie.data.source.remote.ColorRemoteDataSource
import com.example.movie.data.source.remote.FilmRemoteDataSource
import com.example.movie.data.source.remote.PeopleRemoteDataSource
import com.example.movie.data.source.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteFilmDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemotePeopleDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteColorDataSource

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

    @Singleton
    @Provides
    fun providePeopleRepository(
        @RemotePeopleDataSource remotePeopleDataSource: PeopleDataSource
    ): PeopleRepository {
        return DefaultPeopleRepository(remotePeopleDataSource)
    }

    @Singleton
    @Provides
    fun provideColorRepository(
        @RemoteColorDataSource remoteColorDataSource: ColorDataSource
    ): ColorRepository {
        return DefaultColorRepository(remoteColorDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteFilmDataSource
    @Provides
    fun provideFilmRemoteDataSource(): FilmDataSource = FilmRemoteDataSource

    @Singleton
    @RemotePeopleDataSource
    @Provides
    fun providePeopleRemoteDataSource(): PeopleDataSource = PeopleRemoteDataSource

    @Singleton
    @RemoteColorDataSource
    @Provides
    fun provideColorRemoteDataSource(): ColorDataSource = ColorRemoteDataSource
}