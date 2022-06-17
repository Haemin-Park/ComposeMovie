package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.CharacterItem
import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.model.Character

class DefaultCharacterRepository(
    private val characterRemoteDataSource: CharacterDataSource
) : CharacterRepository {
    private val selectedCharacter = MutableLiveData<Character>()

    override suspend fun getCharacters(): Result<List<CharacterItem>> {
        return characterRemoteDataSource.getCharacters()
    }

    override fun selectCharacter(character: Character) {
        selectedCharacter.value = character
    }

    override fun observeCharacterSelected(): LiveData<Character> = selectedCharacter
}