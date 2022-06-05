package com.example.movie

import androidx.navigation.NavHostController

object AppDestinations {
    const val HOME = "home"
    const val PEOPLE = "people"
    const val DETAIL = "detail"
}

class MovieAppNavigationActions(navController: NavHostController) {
    val navigateToDetail: () -> Unit = {
        navController.navigate(AppDestinations.DETAIL)
    }

    val back: () -> Unit = {
        navController.navigateUp()
    }
}