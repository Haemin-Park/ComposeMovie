package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.model.CharacterWithColorCode

class DefaultCharacterRepository(
    private val characterRemoteDataSource: CharacterDataSource,
    private val characterLocalDataSource: CharacterDataSource
) : CharacterRepository {
    private val selectedCharacter = MutableLiveData<CharacterWithColorCode>()

    override fun getCharacters() = characterLocalDataSource.getCharacters()

    override suspend fun refreshCharacters() {
        val remoteData = characterRemoteDataSource.getCharacters()

        remoteData.collect {
            if (it is Result.Success) {
                characterLocalDataSource.saveCharacters(it.data)
            }
        }
    }

    override fun selectCharacter(character: CharacterWithColorCode) {
        selectedCharacter.value = character
    }

    override fun observeCharacterSelected(): LiveData<CharacterWithColorCode> = selectedCharacter
}