package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.movie.model.fakeFilmData
import com.example.movie.ui.film.FilmDetailScreen
import com.example.movie.ui.film.FilmItemView
import com.example.movie.ui.film.FilmsContent
import com.example.movie.ui.film.FilmViewModel
import com.example.movie.ui.theme.MovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieTheme {
        FilmItemView(film = fakeFilmData) {
            
        }
    }
}