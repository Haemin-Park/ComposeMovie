package com.example.movie.data.source

import com.example.movie.data.FilmItem
import com.example.movie.data.Result

interface FilmDataSource {
    suspend fun getFilms() : Result<List<FilmItem>>
}