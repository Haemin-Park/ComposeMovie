package com.example.movie.data.source.repository

import com.example.movie.data.FilmItem
import com.example.movie.data.Result

interface FilmRepository {
    suspend fun getFilms() : Result<List<FilmItem>>
}