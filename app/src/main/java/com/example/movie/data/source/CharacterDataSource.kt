package com.example.movie.data.source

import com.example.movie.data.Result
import com.example.movie.model.Character
import com.example.movie.model.Film

interface CharacterDataSource {
    suspend fun getCharacters() : Result<List<Character>>
    suspend fun saveCharacters(characters: List<Character>)
}