package com.example.movie.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.CharacterWithColorCodeUseCase
import com.example.movie.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    characterWithColorCodeUseCase: CharacterWithColorCodeUseCase
) : ViewModel() {
    val characters = characterWithColorCodeUseCase.character

    private val _selectedCharacter = MutableLiveData<Character>()
    val selectedCharacter: LiveData<Character>
        get() = _selectedCharacter

    init {
        viewModelScope.launch {
            characterWithColorCodeUseCase()
        }
    }

    fun selectPerson(character: Character) {
        _selectedCharacter.value = character
    }
}