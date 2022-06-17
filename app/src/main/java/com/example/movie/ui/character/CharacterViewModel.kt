package com.example.movie.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.source.repository.CharacterRepository
import com.example.movie.domain.CharacterWithColorCodeUseCase
import com.example.movie.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    characterWithColorCodeUseCase: CharacterWithColorCodeUseCase
) : ViewModel() {
    val characters = characterWithColorCodeUseCase.character

    val selectedCharacter = characterRepository.observeCharacterSelected()

    init {
        viewModelScope.launch {
            characterWithColorCodeUseCase()
        }
    }

    fun selectPerson(character: Character) {
        characterRepository.selectCharacter(character)
    }
}