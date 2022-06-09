package com.example.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.film.FilmDetailScreen
import com.example.movie.ui.film.FilmViewModel
import com.example.movie.ui.film.FilmsContent
import com.example.movie.ui.people.PeopleScreen
import com.example.movie.ui.people.PeopleViewModel

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
            val viewModel = hiltViewModel<FilmViewModel>(viewModelStoreOwner = viewModelStoreOwner)

            FilmsContent(
                viewModel = viewModel,
                navigateToDetail = navigationActions.navigateToDetail
            )
        }
        composable(AppDestinations.PEOPLE) {
            val viewModel: PeopleViewModel = viewModel()

            PeopleScreen(viewModel = viewModel)
        }
        composable(AppDestinations.DETAIL) {
            val film by hiltViewModel<FilmViewModel>(viewModelStoreOwner = viewModelStoreOwner).selectedFilm.observeAsState()

            film?.let {
                FilmDetailScreen(
                    film = it,
                    back = navigationActions.back
                )
            }
        }
    }
}