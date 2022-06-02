package com.example.movie

import androidx.navigation.NavHostController

object AppDestinations {
    const val HOME = "home"
    const val DETAIL = "detail"
}

class MovieAppNavigationActions(navController: NavHostController) {
    val navigateToDetail: () -> Unit = {
        navController.navigate(AppDestinations.DETAIL)
    }
}