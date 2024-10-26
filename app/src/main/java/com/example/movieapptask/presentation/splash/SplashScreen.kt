package com.example.movieapptask.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapptask.R
import com.example.movieapptask.presentation.ui.theme.BackgroundColor
import com.example.movieapptask.presentation.ui.theme.ButtonColor
import com.example.movieapptask.presentation.ui.theme.titleLargeStyle
import com.example.movieapptask.presentation.ui.theme.titleSmallStyle
import com.example.movieapptask.utils.Const.HOME_SCREEN

@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.capture),
            contentDescription = stringResource(R.string.splash_image),
            modifier = modifier
                .fillMaxWidth()
                .height(500.dp),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.onboarding),
            style = titleLargeStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.watch_everything_you_want_for_free),
            style = titleSmallStyle,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                navController.navigate(HOME_SCREEN)
            },
            colors = ButtonDefaults.buttonColors(ButtonColor)
        ) {
            Text(
                text = "Get Started",
                style = titleSmallStyle,
                color = Color.White
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}