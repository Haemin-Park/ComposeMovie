package com.example.movie.data.source.remote

import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.data.toModel
import com.example.movie.model.Character
import com.example.movie.network.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object CharacterRemoteDataSource : CharacterDataSource {
    override fun getCharacters(): Flow<Result<List<Character>>> = flow {
        try {
            val response = MovieService.api.getCharacters()
            val responseBody = response.body()
            if (responseBody != null && response.isSuccessful) {
                emit(Result.Success(responseBody.map { it.toModel() }))
            } else {
                emit(Result.Error(response.message() ?: "Something goes wrong"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Internet error runs"))
        }
    }

    override suspend fun saveCharacters(characters: List<Character>) {
    }
}