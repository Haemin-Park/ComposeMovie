package com.example.movie.data.source.remote

import com.example.movie.data.Result
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.data.toModel
import com.example.movie.model.Character
import com.example.movie.network.MovieService

object CharacterRemoteDataSource : CharacterDataSource {
    override suspend fun getCharacters(): Result<List<Character>> {
        return try {
            val response = MovieService.api.getCharacters()
            val responseBody = response.body()
            if (responseBody != null && response.isSuccessful) {
                Result.Success(responseBody.map { it.toModel() })
            } else {
                Result.Error(response.message() ?: "Something goes wrong")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Internet error runs")
        }
    }

    override suspend fun saveCharacters(characters: List<Character>) {
    }
}