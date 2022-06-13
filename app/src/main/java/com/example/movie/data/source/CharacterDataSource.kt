package com.example.movie.data.source

import com.example.movie.data.CharacterItem
import com.example.movie.data.Result

interface CharacterDataSource {
    suspend fun getCharacters() : Result<List<CharacterItem>>
}