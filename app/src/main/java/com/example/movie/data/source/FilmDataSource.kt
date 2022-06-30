package com.example.movie.data.source

import com.example.movie.data.Result
import com.example.movie.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmDataSource {
    fun getFilms(): Flow<Result<List<Film>>>
    suspend fun saveFilms(films: List<Film>)
}