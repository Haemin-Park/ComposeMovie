package com.example.movie.network

import com.example.movie.data.ColorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorApi {
    @GET("v1/names/{name}")
    suspend fun getColorCod(@Path("name") name: String): Response<ColorResponse>
}