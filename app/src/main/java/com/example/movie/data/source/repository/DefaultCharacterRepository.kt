package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.model.Character
import com.example.movie.model.CharacterWithColorCode

class DefaultCharacterRepository(
    private val characterRemoteDataSource: CharacterDataSource
) : CharacterRepository {
    private val selectedCharacter = MutableLiveData<CharacterWithColorCode>()

    override suspend fun getCharacters(): Result<List<Character>> {
        return characterRemoteDataSource.getCharacters()
    }

    override fun selectCharacter(character: CharacterWithColorCode) {
        selectedCharacter.value = character
    }

    override fun observeCharacterSelected(): LiveData<CharacterWithColorCode> = selectedCharacter
}