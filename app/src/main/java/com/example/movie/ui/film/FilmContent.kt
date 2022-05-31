package com.example.movie.ui.film

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.example.movie.AppDestinations
import com.example.movie.R
import com.example.movie.model.fakeFilmData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmsContent(viewModel: FilmViewModel, navController: NavController) {
    val items by viewModel.films.observeAsState()

    items?.let {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_normal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal))
        ) {
            items(items = it) { film ->
                FilmItemView(film = film) {
                    viewModel.selectFilm(film)
                    navController.navigate(AppDestinations.DETAIL)
                }
            }
        }
    }
}