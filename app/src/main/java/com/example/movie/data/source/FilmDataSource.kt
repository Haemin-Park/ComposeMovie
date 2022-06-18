package com.example.movie.data.source

import com.example.movie.data.Result
import com.example.movie.model.Film

interface FilmDataSource {
    suspend fun getFilms() : Result<List<Film>>
}