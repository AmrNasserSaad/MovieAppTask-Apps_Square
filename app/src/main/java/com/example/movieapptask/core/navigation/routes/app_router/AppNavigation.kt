package com.example.movieapptask.core.navigation.routes.app_router

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
            route = "details_screen/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movie")
            val movie = Gson().fromJson(movieJson, Movie::class.java)
            MovieDetailsScreen(navController = navController, movie = movie)
        }
    }

}
