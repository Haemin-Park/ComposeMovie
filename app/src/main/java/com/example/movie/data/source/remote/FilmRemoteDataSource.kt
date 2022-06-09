package com.example.movie.data.source.remote

import com.example.movie.data.FilmItem
import com.example.movie.data.Result
import com.example.movie.data.safeApiCall
import com.example.movie.data.source.FilmDataSource
import com.example.movie.network.MovieService

object FilmRemoteDataSource : FilmDataSource {
    override suspend fun getFilms(): Result<List<FilmItem>> {
        return safeApiCall { MovieService.api.getFilms() }
    }
}