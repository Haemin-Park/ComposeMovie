package com.example.movie.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.Color
import com.example.movie.data.Result
import com.example.movie.data.defaultColor
import com.example.movie.data.source.repository.CharacterRepository
import com.example.movie.data.source.repository.ColorRepository
import com.example.movie.model.Character
import com.example.movie.model.CharacterWithColorCode
import com.example.movie.model.addColorCode
import javax.inject.Inject

class CharacterWithColorCodeUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val colorRepository: ColorRepository
) {
    private val _character = MutableLiveData<List<CharacterWithColorCode>>(listOf())
    val character: LiveData<List<CharacterWithColorCode>>
        get() = _character

    suspend operator fun invoke() {
        characterRepository.getCharacters().collect {
            if (it is Result.Success) {
                it.data.map { c ->
                    _character.value = _character.value?.plus(c.addColorCode(getColorCode(c).rgb))
                }
            }
        }
    }

    private suspend fun getColorCode(person: Character): Color {
        return when (val result = colorRepository.getColorCode(person.hairColor)
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