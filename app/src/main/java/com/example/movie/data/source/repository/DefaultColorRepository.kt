package com.example.movie.data.source.repository

import com.example.movie.data.ColorResponse
import com.example.movie.data.Result
import com.example.movie.data.source.ColorDataSource

class DefaultColorRepository(
    private val colorRemoteDataSource: ColorDataSource
) : ColorRepository {
    override suspend fun getColorCode(name: String): Result<ColorResponse> {
        return colorRemoteDataSource.getColorCode(name)
    }
}