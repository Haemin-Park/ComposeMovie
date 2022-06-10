package com.example.movie.ui.film

import androidx.lifecycle.*
import com.example.movie.data.Result
import com.example.movie.data.source.repository.FilmRepository
import com.example.movie.data.toModel
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

    private val _selectedFilm = MutableLiveData<Film>()
    val selectedFilm: LiveData<Film>
        get() = _selectedFilm

    init {
        getFilms()
    }

    private fun getFilms() {
        viewModelScope.launch {
            when (val result = filmRepository.getFilms()) {
                is Result.Success -> {
                    _films.value = result.data.map { it.toModel() }
                }
                is Result.Error -> {

                }
            }
        }
    }

    fun selectFilm(film: Film){
        _selectedFilm.value = film
    }
}