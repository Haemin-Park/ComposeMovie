package com.example.movie.ui.film

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.R
import com.example.movie.model.Film

@Composable
fun FilmDetailScreen(film: Film) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(R.drawable.placeholder)
            .data(film.movieBanner)
            .crossfade(true)
            .build()
    )

    Column() {
        Image(painter = painter, contentDescription = null)
        Text(text = film.title)
        Text(text = film.director)
    }
}