package com.example.movie.data.source.remote

import com.example.movie.data.Result
import com.example.movie.data.source.FilmDataSource
import com.example.movie.data.toModel
import com.example.movie.model.Film
import com.example.movie.network.MovieService

object FilmRemoteDataSource : FilmDataSource {
    override suspend fun getFilms(): Result<List<Film>> {
        return try {
            val response = MovieService.api.getFilms()
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
}