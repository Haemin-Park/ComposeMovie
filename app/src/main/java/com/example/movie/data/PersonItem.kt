package com.example.movie.data

import androidx.compose.ui.graphics.Color
import com.example.movie.model.Person

data class PersonItem(
    val age: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val id: String,
    val name: String,
    val species: String,
    val url: String
)

fun PersonItem.toModel(hairRgb: Rgb) = Person(
    id = id,
    hairColor = Color(hairRgb.r, hairRgb.g, hairRgb.b),
    name = name
)