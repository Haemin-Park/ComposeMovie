package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import com.example.movie.data.Result
import com.example.movie.model.Character
import com.example.movie.model.CharacterWithColorCode
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<Result<List<Character>>>
    suspend fun refreshCharacters()
    fun selectCharacter(character: CharacterWithColorCode)
    fun observeCharacterSelected(): LiveData<CharacterWithColorCode>
}