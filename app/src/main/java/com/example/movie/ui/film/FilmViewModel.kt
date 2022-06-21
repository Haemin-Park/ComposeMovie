package com.example.movie.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.Result
import com.example.movie.data.source.repository.FilmRepository
import com.example.movie.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmRepository: FilmRepository
) : ViewModel() {
    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>>
        get() = _films

    val selectedFilm = filmRepository.observeFilmSelected()

    init {
        viewModelScope.launch {
            filmRepository.refreshFilms()
        }
        getFilms()
    }

    private fun getFilms() {
        viewModelScope.launch {
            when (val result = filmRepository.getFilms()) {
                is Result.Success -> {
                    _films.value = result.data
                }
                is Result.Error -> {

                }
            }
        }
    }

    fun selectFilm(film: Film) {
        filmRepository.selectFilm(film)
    }
}