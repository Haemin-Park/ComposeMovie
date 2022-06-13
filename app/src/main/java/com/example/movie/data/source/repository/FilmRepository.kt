package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import com.example.movie.data.FilmItem
import com.example.movie.data.Result
import com.example.movie.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getFilms() : Result<List<FilmItem>>
    fun selectedFilm(film: Film)
    fun observeFilmSelected() : LiveData<Film>
}