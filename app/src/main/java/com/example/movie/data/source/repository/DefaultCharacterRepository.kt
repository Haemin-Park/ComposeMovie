package com.example.movie.data.source.repository

import com.example.movie.data.CharacterItem
import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource

class DefaultCharacterRepository(
    private val peopleRemoteDataSource: CharacterDataSource
) : CharacterRepository {
    override suspend fun getCharacters(): Result<List<CharacterItem>> {
        return peopleRemoteDataSource.getCharacters()
    }
}