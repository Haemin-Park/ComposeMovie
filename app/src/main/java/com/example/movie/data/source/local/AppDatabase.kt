package com.example.movie.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.model.Character
import com.example.movie.model.Film

@Database(entities = [Film::class, Character::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun CharacterDao(): CharacterDao
}