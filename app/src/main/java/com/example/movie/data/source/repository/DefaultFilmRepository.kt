package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.FilmItem
import com.example.movie.data.Result
import com.example.movie.data.source.FilmDataSource
import com.example.movie.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultFilmRepository(
    private val filmRemoteDataSource: FilmDataSource
): FilmRepository {
    private val selectedFilm = MutableLiveData<Film>()

    override suspend fun getFilms(): Result<List<FilmItem>> {
        return filmRemoteDataSource.getFilms()
    }

    override fun selectFilm(film: Film) {
        selectedFilm.value = film
    }

    override fun observeFilmSelected(): LiveData<Film> = selectedFilm
}