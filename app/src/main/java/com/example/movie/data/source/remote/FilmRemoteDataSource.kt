package com.example.movie.data.source.remote

import com.example.movie.data.Result
import com.example.movie.data.source.FilmDataSource
import com.example.movie.data.toModel
import com.example.movie.model.Film
import com.example.movie.network.MovieService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

object FilmRemoteDataSource : FilmDataSource {
    override fun getFilms() = flow {
        try {
            val response = MovieService.api.getFilms()
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

    override suspend fun saveFilms(films: List<Film>) {
    }
}