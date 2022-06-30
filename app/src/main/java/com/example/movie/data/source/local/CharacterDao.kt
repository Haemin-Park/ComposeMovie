package com.example.movie.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.model.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(vararg character: Character)

    @Query("SELECT * FROM Characters")
    suspend fun getCharacters(): List<Character>
}