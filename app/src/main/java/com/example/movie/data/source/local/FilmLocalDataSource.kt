package com.example.movie.data.source.local

import com.example.movie.data.Result
import com.example.movie.data.source.FilmDataSource
import com.example.movie.model.Film
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FilmLocalDataSource(
    private val filmDao: FilmDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FilmDataSource {
    override fun getFilms(): Flow<Result<List<Film>>> {
        return filmDao.getFilms().map { Result.Success(it) }
    }

    override suspend fun saveFilms(films: List<Film>) {
        withContext(ioDispatcher) {
            filmDao.insertFilms(*films.toTypedArray())
        }
    }
}