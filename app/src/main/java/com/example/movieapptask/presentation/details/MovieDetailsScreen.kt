package com.example.movieapptask.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieDetailsScreen(navController: NavController, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(rememberNavController())
}