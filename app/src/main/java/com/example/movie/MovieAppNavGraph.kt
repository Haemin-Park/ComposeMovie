package com.example.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.Screen
import com.example.movie.ui.film.FilmViewModel
import com.example.movie.ui.film.FilmsContent
import com.example.movie.ui.film.FilmDetailScreen
import com.example.movie.ui.character.CharacterScreen
import com.example.movie.ui.character.CharacterViewModel

@Composable
fun MovieAppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    val navigationActions = remember(navController) {
        MovieAppNavigationActions(navController)
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<FilmViewModel>(viewModelStoreOwner = viewModelStoreOwner)

            FilmsContent(
                viewModel = viewModel,
                navigateToDetail = navigationActions.navigateToDetail
            )
        }
        composable(Screen.Character.route) {
            val viewModel = hiltViewModel<CharacterViewModel>()

            CharacterScreen(viewModel = viewModel)
        }
        composable(Screen.Detail.route) {
            val viewModel = hiltViewModel<FilmViewModel>(viewModelStoreOwner = viewModelStoreOwner)

            FilmDetailScreen(
                viewModel = viewModel,
                back = navigationActions.back
            )
        }
    }
}