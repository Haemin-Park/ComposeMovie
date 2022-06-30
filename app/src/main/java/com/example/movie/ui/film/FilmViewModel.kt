package com.example.movie.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.Result
import com.example.movie.data.source.repository.FilmRepository
import com.example.movie.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmRepository: FilmRepository
) : ViewModel() {
    val films: LiveData<List<Film>?> = filmRepository.getFilms().map {
        if (it is Result.Success) it.data else null
    }.asLiveData()

    val selectedFilm = filmRepository.observeFilmSelected()

    init {
        viewModelScope.launch {
            filmRepository.refreshFilms()
        }
    }

    fun selectFilm(film: Film) {
        filmRepository.selectFilm(film)
    }
}