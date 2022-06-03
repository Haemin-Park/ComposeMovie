package com.example.movie.ui.film

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.R
import com.example.movie.model.Film
import com.example.movie.ui.theme.MovieTheme

@Composable
fun FilmDetailScreen(film: Film) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(R.drawable.placeholder)
            .data(film.movieBanner)
            .build()
    )

    Column() {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(text = film.title)
        Text(text = film.director)
    }
}

@Preview(showBackground = true)
@Composable
fun FilmDetailScreenPreview(@PreviewParameter(FilmItemViewPreviewParameterProvider::class) film: Film) {
    MovieTheme {
        FilmDetailScreen(film = film)
    }
}