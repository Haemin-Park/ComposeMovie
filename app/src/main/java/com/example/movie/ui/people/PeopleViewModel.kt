package com.example.movie.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.*
import com.example.movie.data.Color
import com.example.movie.data.defaultColor
import com.example.movie.model.Person
import com.example.movie.network.ColorService
import com.example.movie.network.MovieService
import kotlinx.coroutines.launch

class PeopleViewModel : ViewModel() {
    private val _people = MutableLiveData<List<Person>>()
    val people: LiveData<List<Person>>
        get() = _people

    private val _selectedPerson = MutableLiveData<Person>()
    val selectedPerson: LiveData<Person>
        get() = _selectedPerson

    init {
        getPeople()
    }

    private fun getPeople() {
        viewModelScope.launch {
            when (val result = safeApiCall { MovieService.api.getPeople() }) {
                is Result.Success -> {
                    _people.value = result.data.map {
                        it.toModel(getColorCode(it).rgb)
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }

    private suspend fun getColorCode(person: PersonItem): Color {
        return when (val result = safeApiCall {
            ColorService.api.getColorCod(person.hair_color)
        }) {
            is Result.Success -> {
                if (result.data.colors.isNotEmpty()) result.data.colors[0] else defaultColor
            }
            is Result.Error -> {
                defaultColor
            }
        }
    }

    fun selectPerson(person: Person){
        _selectedPerson.value = person
    }
}