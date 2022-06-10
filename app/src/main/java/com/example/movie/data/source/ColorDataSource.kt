package com.example.movie.data.source

import com.example.movie.data.ColorResponse
import com.example.movie.data.Result

interface ColorDataSource {
    suspend fun getColorCode(name: String): Result<ColorResponse>
}