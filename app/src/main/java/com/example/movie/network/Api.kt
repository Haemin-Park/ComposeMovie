package com.example.movie.network

import com.example.movie.data.FilmItem
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("films")
    suspend fun getFilms(): Response<List<FilmItem>>
}