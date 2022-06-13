package com.example.movie.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.*
import com.example.movie.data.source.repository.ColorRepository
import com.example.movie.data.source.repository.CharacterRepository
import com.example.movie.model.Character
import javax.inject.Inject

class CharacterWithColorCodeUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val colorRepository: ColorRepository
) {
    private val _character = MutableLiveData<List<Character>>(listOf())
    val character: LiveData<List<Character>>
        get() = _character

    suspend operator fun invoke() {
        when (val result = characterRepository.getCharacters()) {
            is Result.Success -> {
                result.data.map {
                    _character.value = _character.value?.plus(it.toModel(getColorCode(it).rgb))
                }
            }
            is Result.Error -> {

            }
        }
    }

    private suspend fun getColorCode(person: CharacterItem): Color {
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