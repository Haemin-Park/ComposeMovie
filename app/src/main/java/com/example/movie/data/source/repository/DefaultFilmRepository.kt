package com.example.movie.data.source.repository

import com.example.movie.data.FilmItem
import com.example.movie.data.Result
import com.example.movie.data.source.FilmDataSource

class DefaultFilmRepository(
    private val filmRemoteDataSource: FilmDataSource
): FilmRepository {
    override suspend fun getFilms(): Result<List<FilmItem>> {
        return filmRemoteDataSource.getFilms()
    }
}