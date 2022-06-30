package com.example.movie.di

import android.content.Context
import androidx.room.Room
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.data.source.ColorDataSource
import com.example.movie.data.source.FilmDataSource
import com.example.movie.data.source.local.AppDatabase
import com.example.movie.data.source.local.FilmLocalDataSource
import com.example.movie.data.source.remote.CharacterRemoteDataSource
import com.example.movie.data.source.remote.ColorRemoteDataSource
import com.example.movie.data.source.remote.FilmRemoteDataSource
import com.example.movie.data.source.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteFilmDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalFilmDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteCharacterDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteColorDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFilmRepository(
        @RemoteFilmDataSource remoteFilmDataSource: FilmDataSource,
        @LocalFilmDataSource localFilmDataSource: FilmDataSource
    ): FilmRepository {
        return DefaultFilmRepository(remoteFilmDataSource, localFilmDataSource)
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(
        @RemoteCharacterDataSource remoteCharacterDataSource: CharacterDataSource
    ): CharacterRepository {
        return DefaultCharacterRepository(remoteCharacterDataSource)
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
    @LocalFilmDataSource
    @Provides
    fun provideFilmLocalDataSource(
        database: AppDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): FilmDataSource {
        return FilmLocalDataSource(database.filmDao(), ioDispatcher)
    }

    @Singleton
    @RemoteCharacterDataSource
    @Provides
    fun provideCharacterRemoteDataSource(): CharacterDataSource = CharacterRemoteDataSource

    @Singleton
    @RemoteColorDataSource
    @Provides
    fun provideColorRemoteDataSource(): ColorDataSource = ColorRemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "film.db"
        ).build()
    }
}