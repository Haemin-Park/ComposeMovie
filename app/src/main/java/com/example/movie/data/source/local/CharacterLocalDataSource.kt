package com.example.movie.data.source.local

import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterLocalDataSource(
    private val characterDao: CharacterDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharacterDataSource {
    override suspend fun getCharacters(): Result<List<Character>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(characterDao.getCharacters())
        } catch (e: Exception) {
            Result.Error(e.message ?: "Something goes wrong")
        }
    }

    override suspend fun saveCharacters(characters: List<Character>) {
        withContext(ioDispatcher) {
            characterDao.insertCharacters(*characters.toTypedArray())
        }
    }
}