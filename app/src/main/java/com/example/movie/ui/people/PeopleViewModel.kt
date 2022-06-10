package com.example.movie.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.*
import com.example.movie.data.source.repository.ColorRepository
import com.example.movie.data.source.repository.PeopleRepository
import com.example.movie.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val colorRepository: ColorRepository
) : ViewModel() {
    private val _people = MutableLiveData<List<Person>>(listOf())
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
            when (val result = peopleRepository.getPeople()) {
                is Result.Success -> {
                    result.data.map {
                        _people.value = _people.value?.plus(it.toModel(getColorCode(it).rgb))
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }

    private suspend fun getColorCode(person: PersonItem): Color {
        return when (val result = colorRepository.getColorCode(person.hair_color)
        ) {
            is Result.Success -> {
                if (result.data.colors.isNotEmpty()) result.data.colors[0] else defaultColor
            }
            is Result.Error -> {
                defaultColor
            }
        }
    }

    fun selectPerson(person: Person) {
        _selectedPerson.value = person
    }
}