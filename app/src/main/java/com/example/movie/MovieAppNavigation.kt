package com.example.movie

import androidx.navigation.NavHostController
import com.example.movie.ui.Screen

class MovieAppNavigationActions(navController: NavHostController) {
    val navigateToDetail: () -> Unit = {
        navController.navigate(Screen.Detail.route)
    }

    val back: () -> Unit = {
        navController.navigateUp()
    }
}