package com.example.movie.data.source

import com.example.movie.data.Result
import com.example.movie.model.Character
import com.example.movie.model.Film
import kotlinx.coroutines.flow.Flow

interface CharacterDataSource {
    fun getCharacters() : Flow<Result<List<Character>>>
    suspend fun saveCharacters(characters: List<Character>)
}