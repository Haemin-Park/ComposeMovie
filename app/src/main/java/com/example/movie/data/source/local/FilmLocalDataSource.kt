package com.example.movie.data.source.local

import com.example.movie.data.Result
import com.example.movie.data.source.FilmDataSource
import com.example.movie.model.Film
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmLocalDataSource(
    private val filmDao: FilmDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FilmDataSource {
    override suspend fun getFilms(): Result<List<Film>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(filmDao.getFilms())
        } catch (e: Exception) {
            Result.Error(e.message ?: "Something goes wrong")
        }
    }
}