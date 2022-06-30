package com.example.movie.data

import com.example.movie.model.Character

data class CharacterItem(
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

fun CharacterItem.toModel() = Character(
    id = id,
    hairColor = hair_color,
    name = name
)