package com.example.movie.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ColorService {
    private const val BASE_URL = "https://api.color.pizza/"
    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

    val api: ColorApi = retrofit.create(ColorApi::class.java)
}