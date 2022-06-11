package com.example.movie.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.*
import com.example.movie.data.source.repository.ColorRepository
import com.example.movie.data.source.repository.PeopleRepository
import com.example.movie.model.Person
import javax.inject.Inject

class PeopleWithColorUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val colorRepository: ColorRepository
) {
    private val _people = MutableLiveData<List<Person>>(listOf())
    val people: LiveData<List<Person>>
        get() = _people

    suspend operator fun invoke() {
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
}