package com.example.movie.network

import com.example.movie.data.FilmItem
import com.example.movie.data.CharacterItem
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("films")
    suspend fun getFilms(): Response<List<FilmItem>>

    @GET("people")
    suspend fun getCharacters(): Response<List<CharacterItem>>
}