package com.example.movie.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movie.data.Rgb

@Entity(tableName = "Characters")
data class Character(
    @PrimaryKey
    val id: String,
    val hairColor: String,
    val name: String
)

fun Character.addColorCode(hairRgb: Rgb) = CharacterWithColorCode(
    id = id,
    hairColor = Color(hairRgb.r, hairRgb.g, hairRgb.b),
    name = name
)