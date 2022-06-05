package com.example.movie.model

data class Film(
    val director: String,
    val image: String,
    val movieBanner: String,
    val score: String,
    val title: String,
    val originalTitle: String,
    val description: String,
    val releaseDate: String,
    val runningTime: String
)

val fakeFilmDatas = arrayOf(
    Film(
        "Hayao Miyazaki",
        "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        "95",
        "Castle in the Sky",
        "天空の城ラピュタ",
        "This is a very very very very very very very very very very very very very very very very very very very very very very very very very very very long text",
        "1986",
        "124"
    ),
    Film(
        "Isao Takahata",
        "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        "100",
        "The Tale of the Princess Kaguya",
        "もののけ姫",
        "This is a very very very very very very very very very very very very very very very very very very very very very very very very very very very long text",
        "2013",
        "137"
    )
)