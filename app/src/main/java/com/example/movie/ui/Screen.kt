package com.example.movie.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movie.AppDestinations
import com.example.movie.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val iconVector: ImageVector) {
    object Home : Screen(AppDestinations.HOME, R.string.movie, Icons.Filled.Home)
    object People : Screen(AppDestinations.PEOPLE, R.string.people, Icons.Filled.Person)
}