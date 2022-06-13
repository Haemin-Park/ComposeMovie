package com.example.movie.data.source.repository

import com.example.movie.data.CharacterItem
import com.example.movie.data.Result

interface CharacterRepository {
    suspend fun getCharacters() : Result<List<CharacterItem>>
}