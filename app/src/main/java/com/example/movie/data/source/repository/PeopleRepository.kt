package com.example.movie.data.source.repository

import com.example.movie.data.PersonItem
import com.example.movie.data.Result

interface PeopleRepository {
    suspend fun getPeople() : Result<List<PersonItem>>
}