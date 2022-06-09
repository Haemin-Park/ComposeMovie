package com.example.movie.data.source

import com.example.movie.data.FilmItem
import com.example.movie.data.Result

class DefaultFilmRepository(
    private val filmRemoteDataSource: FilmDataSource
): FilmRepository {
    override suspend fun getFilms(): Result<List<FilmItem>> {
        return filmRemoteDataSource.getFilms()
    }
}