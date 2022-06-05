package com.example.movie.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.Result
import com.example.movie.data.safeApiCall
import com.example.movie.data.toModel
import com.example.movie.model.Film
import com.example.movie.network.MovieService
import kotlinx.coroutines.launch

class FilmViewModel : ViewModel() {
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
            when (val result = safeApiCall { MovieService.api.getFilms() }) {
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