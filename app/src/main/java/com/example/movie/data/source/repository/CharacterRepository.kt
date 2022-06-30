package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import com.example.movie.data.CharacterItem
import com.example.movie.data.Result
import com.example.movie.model.Character
import com.example.movie.model.CharacterWithColorCode

interface CharacterRepository {
    suspend fun getCharacters() : Result<List<Character>>
    fun selectCharacter(character: CharacterWithColorCode)
    fun observeCharacterSelected() : LiveData<CharacterWithColorCode>
}