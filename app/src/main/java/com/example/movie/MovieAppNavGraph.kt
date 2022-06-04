package com.example.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.film.FilmDetailScreen
import com.example.movie.ui.film.FilmViewModel
import com.example.movie.ui.film.FilmsContent

@Composable
fun MovieAppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.HOME
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    val navigationActions = remember(navController) {
        MovieAppNavigationActions(navController)
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppDestinations.HOME) {
            val viewModel: FilmViewModel = viewModel(viewModelStoreOwner = viewModelStoreOwner)

            FilmsContent(
                viewModel = viewModel,
                navigateToDetail = navigationActions.navigateToDetail
            )
        }
        composable(AppDestinations.DETAIL) {
            val film by viewModel<FilmViewModel>(viewModelStoreOwner = viewModelStoreOwner).selectedFilm.observeAsState()

            film?.let {
                FilmDetailScreen(
                    film = it,
                    back = navigationActions.back
                )
            }
        }
    }
}