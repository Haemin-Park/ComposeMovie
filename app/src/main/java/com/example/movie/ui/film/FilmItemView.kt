package com.example.movie.ui.film

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.R
import com.example.movie.model.Film

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilmItemView(film: Film, onClick: () -> Unit) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(R.drawable.placeholder)
            .data(film.movieBanner)
            .crossfade(true)
            .build()
    )

    Card(
        onClick = onClick
    ) {
        Column() {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )
            Text(text = film.title)
            Text(text = film.score)
        }
    }
}