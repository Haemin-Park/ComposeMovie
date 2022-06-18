package com.example.movie.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.model.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}