package com.example.movie.data.source.repository

import com.example.movie.data.PersonItem
import com.example.movie.data.Result
import com.example.movie.data.source.PeopleDataSource

class DefaultPeopleRepository(
    private val peopleRemoteDataSource: PeopleDataSource
) : PeopleRepository {
    override suspend fun getPeople(): Result<List<PersonItem>> {
        return peopleRemoteDataSource.getPeople()
    }
}