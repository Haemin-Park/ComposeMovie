package com.example.movie.data

data class Color(
    val name: String,
    val rgb: Rgb
)

val defaultColor = Color("default", Rgb(0, 0, 0))