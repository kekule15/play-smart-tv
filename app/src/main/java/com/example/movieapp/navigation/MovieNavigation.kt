package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.home.DetailsPage
import com.example.movieapp.screens.home.HomePage

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MoviesScreens.HomeScreen.name) {
        composable(MoviesScreens.HomeScreen.name) {
            HomePage(navController = navController)
        }
        composable(MoviesScreens.DetailsScreen.name + "/{movie}", arguments = listOf(
            navArgument(name = "movie") { type = NavType.StringType }
        )) {backStackEntry ->
            DetailsPage(navController = navController, backStackEntry.arguments?.getString("movie"))
        }
    }
}