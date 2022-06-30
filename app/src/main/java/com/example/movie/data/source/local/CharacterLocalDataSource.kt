package com.example.movie.data.source.local

import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CharacterLocalDataSource(
    private val characterDao: CharacterDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharacterDataSource {
    override fun getCharacters(): Flow<Result<List<Character>>> {
        return characterDao.getCharacters().map { Result.Success(it) }
    }

    override suspend fun saveCharacters(characters: List<Character>) {
        withContext(ioDispatcher) {
            characterDao.insertCharacters(*characters.toTypedArray())
        }
    }
}