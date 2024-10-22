package com.example.movieapptask.core.navigation.routes.app_router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapptask.presentation.details.MovieDetailsScreen
import com.example.movieapptask.presentation.home.HomeScreen
import com.example.movieapptask.presentation.splash.SplashScreen
import com.example.movieapptask.utils.Const.DETAILS_SCREEN
import com.example.movieapptask.utils.Const.HOME_SCREEN
import com.example.movieapptask.utils.Const.SPLASH_SCREEN

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SPLASH_SCREEN){
        composable(SPLASH_SCREEN) {
            SplashScreen(navController = navController)
        }
        composable(HOME_SCREEN) {
            HomeScreen(navController = navController)
        }
        composable(DETAILS_SCREEN) {
           MovieDetailsScreen(navController = navController)
        }
    }
}