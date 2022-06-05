package com.example.movie.network

import com.example.movie.data.FilmItem
import com.example.movie.data.PersonItem
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("films")
    suspend fun getFilms(): Response<List<FilmItem>>

    @GET("people")
    suspend fun getPeople(): Response<List<PersonItem>>
}