package com.example.movie.data.source.repository

import com.example.movie.data.ColorResponse
import com.example.movie.data.Result

interface ColorRepository {
    suspend fun getColorCode(name: String): Result<ColorResponse>
}