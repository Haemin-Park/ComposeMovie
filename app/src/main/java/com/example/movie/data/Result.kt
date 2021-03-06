package com.example.movie.data

import retrofit2.Response

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
}

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
    return try {
        val response: Response<T> = call.invoke()
        val responseBody = response.body()
        if (responseBody != null && response.isSuccessful) {
            Result.Success(responseBody)
        } else {
            Result.Error(response.message() ?: "Something goes wrong")
        }
    } catch (e: Exception) {
        Result.Error(e.message ?: "Internet error runs")
    }
}