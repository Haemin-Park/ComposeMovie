package com.example.movie.data

import com.example.movie.model.Film

data class FilmItem(
    val description: String,
    val director: String,
    val id: String,
    val image: String,
    val locations: List<String>,
    val movie_banner: String,
    val original_title: String,
    val original_title_romanised: String,
    val people: List<String>,
    val producer: String,
    val release_date: String,
    val rt_score: String,
    val running_time: String,
    val species: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)

fun FilmItem.toModel() = Film(
    director = director,
    movieBanner = movie_banner,
    score = rt_score,
    title = title,
    description = description
)