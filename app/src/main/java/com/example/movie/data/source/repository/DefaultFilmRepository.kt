package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.Result
import com.example.movie.data.source.FilmDataSource
import com.example.movie.model.Film

class DefaultFilmRepository(
    private val filmRemoteDataSource: FilmDataSource,
    private val filmLocalDataSource: FilmDataSource
) : FilmRepository {
    private val selectedFilm = MutableLiveData<Film>()

    override suspend fun getFilms(): Result<List<Film>> {
        return filmLocalDataSource.getFilms()
    }

    override suspend fun refreshFilms() {
        val remoteData = filmRemoteDataSource.getFilms()

        if (remoteData is Result.Success) {
            filmLocalDataSource.saveFilms(remoteData.data)
        }
    }

    override fun selectFilm(film: Film) {
        selectedFilm.value = film
    }

    override fun observeFilmSelected(): LiveData<Film> = selectedFilm
}