package com.example.movieapptask.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.presentation.details.MovieDetailsScreen
import com.example.movieapptask.presentation.home.HomeScreen
import com.example.movieapptask.presentation.splash.SplashScreen
import com.example.movieapptask.utils.Const.DETAILS_SCREEN
import com.example.movieapptask.utils.Const.HOME_SCREEN
import com.example.movieapptask.utils.Const.SPLASH_SCREEN
import com.google.gson.Gson

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        composable(SPLASH_SCREEN) {
            SplashScreen(navController = navController)
        }
        composable(HOME_SCREEN) {
            HomeScreen(navController = navController)
        }
        composable(
            route = "$DETAILS_SCREEN/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailsScreen(navController = navController, movieId = movieId)
        }
    }

}
