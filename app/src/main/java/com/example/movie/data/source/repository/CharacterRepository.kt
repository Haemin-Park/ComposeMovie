package com.example.movie.data.source.repository

import androidx.lifecycle.LiveData
import com.example.movie.data.CharacterItem
import com.example.movie.data.Result
import com.example.movie.model.Character

interface CharacterRepository {
    suspend fun getCharacters() : Result<List<CharacterItem>>
    fun selectCharacter(character: Character)
    fun observeCharacterSelected() : LiveData<Character>
}