package com.example.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.Screen
import com.example.movie.ui.theme.MovieTheme

@Composable
fun MovieApp() {
    MovieTheme {
        val navController = rememberNavController()

        val bottomBarItems = listOf(
            Screen.Home,
            Screen.People,
        )

        Scaffold(
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomBarItems.forEach {
                    if (it.route == currentDestination?.route) {
                        BottomNavigation() {
                            bottomBarItems.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            imageVector = screen.iconVector,
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination.hierarchy.any { it.route == screen.route },
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }

                        return@forEach
                    }
                }
            }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MovieAppNavGraph(
                    navController = navController
                )
            }
        }
    }
}