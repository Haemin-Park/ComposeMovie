package com.example.movie.model

data class Film(
    val director: String,
    val movieBanner: String,
    val score: String,
    val title: String
)

val fakeFilmDatas = arrayOf(
    Film("Hayao Miyazaki", "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg", "95", "Castle in the Sky"),
    Film("Isao Takahata", "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg", "100", "The Tale of the Princess Kaguya")
)