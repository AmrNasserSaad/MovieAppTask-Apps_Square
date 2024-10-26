package com.example.movieapptask.presentation.details


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapptask.R
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.presentation.ui.theme.Axiforma
import com.example.movieapptask.presentation.ui.theme.ButtonColor
import com.example.movieapptask.utils.Const.BASE_IMG
import com.example.movieapptask.utils.Const.HOME_SCREEN
import com.example.movieapptask.utils.UIState

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int,
) {

    val movieDetailsState by viewModel.movieDetailsState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }


    MovieDetailsState(movieDetailsState, navController)
}

@Composable
private fun MovieDetailsState(
    movieDetailsState: UIState<Movie>,
    navController: NavController
) {
    when (movieDetailsState) {
        is UIState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is UIState.Success -> {
            val movie = movieDetailsState.data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box {
                    // Poster Image using Coil
                    AsyncImage(
                        model = BASE_IMG + movie.poster_path,
                        contentDescription = movie.title,
                        placeholder = painterResource(id = R.drawable.movie_img),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )

                    // Back Arrow Icon
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(28.dp)
                            .size(32.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(ButtonColor)
                            .padding(4.dp)
                            .clickable {
                                navController.navigate(HOME_SCREEN)
                            }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column(modifier = Modifier.padding(horizontal = 28.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = movie.title,
                            fontFamily = Axiforma,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 32.sp,
                            maxLines = 1,
                            modifier = Modifier.weight(0.8f),
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = "${movie.vote_average} Reviews",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Black,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .alpha(0.8f)
                                .weight(0.2f)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black,
                        fontSize = 12.sp,
                        modifier = Modifier.alpha(0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black,
                        fontSize = 12.sp,
                        modifier = Modifier.alpha(0.4f)
                    )
                }
            }
        }

        is UIState.Error -> {
            Text(
                text = "Error loading movie details: ${movieDetailsState.message}",
                color = Color.Red
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        rememberNavController(),
        movieId = 1
    )
}
