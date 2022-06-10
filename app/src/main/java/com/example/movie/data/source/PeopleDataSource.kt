package com.example.movie.data.source

import com.example.movie.data.PersonItem
import com.example.movie.data.Result

interface PeopleDataSource {
    suspend fun getPeople() : Result<List<PersonItem>>
}