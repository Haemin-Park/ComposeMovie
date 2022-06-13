package com.example.movie.data.source.remote

import com.example.movie.data.CharacterItem
import com.example.movie.data.Result
import com.example.movie.data.safeApiCall
import com.example.movie.data.source.CharacterDataSource
import com.example.movie.network.MovieService

object CharacterRemoteDataSource : CharacterDataSource {
    override suspend fun getCharacters(): Result<List<CharacterItem>> =
        safeApiCall { MovieService.api.getCharacters() }
}