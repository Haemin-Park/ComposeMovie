package com.example.movie.data.source.remote

import com.example.movie.data.ColorResponse
import com.example.movie.data.Result
import com.example.movie.data.safeApiCall
import com.example.movie.data.source.ColorDataSource
import com.example.movie.network.ColorService

object ColorRemoteDataSource : ColorDataSource {
    override suspend fun getColorCode(name: String): Result<ColorResponse> =
        safeApiCall { ColorService.api.getColorCod(name) }
}