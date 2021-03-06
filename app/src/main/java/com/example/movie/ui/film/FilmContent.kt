package com.example.movie.ui.film


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.movie.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmsContent(viewModel: FilmViewModel, navigateToDetail: () -> Unit) {
    val items by viewModel.films.observeAsState()

    items?.let {
//        CircularList(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            FilmView(film = it[0]) {
//
//            }
//            FilmView(film = it[1]) {
//
//            }
//            FilmView(film = it[2]) {
//
//            }
//            FilmView(film = it[3]) {
//
//            }
//            FilmView(film = it[4]) {
//
//            }
//            FilmView(film = it[5]) {
//
//            }
//            FilmView(film = it[6]) {
//
//            }
//            FilmView(film = it[7]) {
//
//            }
//        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_normal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal))
        ) {
            items(items = it) { film ->
                FilmView(film = film) {
                    viewModel.selectFilm(film)
                    navigateToDetail()
                }
            }
        }
    }
}