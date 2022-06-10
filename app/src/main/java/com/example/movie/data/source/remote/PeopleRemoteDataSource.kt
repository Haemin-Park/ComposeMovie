package com.example.movie.data.source.remote

import com.example.movie.data.PersonItem
import com.example.movie.data.Result
import com.example.movie.data.safeApiCall
import com.example.movie.data.source.PeopleDataSource
import com.example.movie.network.MovieService

object PeopleRemoteDataSource : PeopleDataSource {
    override suspend fun getPeople(): Result<List<PersonItem>> =
        safeApiCall { MovieService.api.getPeople() }
}