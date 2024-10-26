package com.example.movieapptask.presentation.home.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapptask.R
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.utils.Const.BASE_IMG
import com.example.movieapptask.utils.Const.DETAILS_SCREEN
import com.example.movieapptask.utils.UIState

@Composable
fun MovieState(movieState: UIState<List<Movie>>, navController: NavController) {
    when (movieState) {
        is UIState.Loading -> CircularProgressIndicator()
        is UIState.Success -> MovieGrid(movieState.data, navController = navController)
        is UIState.Error -> Toast.makeText(
            LocalContext.current,
            movieState.message,
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun MovieGrid(movies: List<Movie>, navController: NavController) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(movies) { movie ->
            MovieItem(movie = movie, navController = navController)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp)
            .clickable {
                navController.navigate("$DETAILS_SCREEN/${movie.id}")
            }
    ) {

        AsyncImage(
            model = BASE_IMG + movie.poster_path,
            contentDescription = movie.title,
            placeholder = painterResource(R.drawable.capture),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
        )


        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Text(
            text = movie.release_date.take(4), // Extract year
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}