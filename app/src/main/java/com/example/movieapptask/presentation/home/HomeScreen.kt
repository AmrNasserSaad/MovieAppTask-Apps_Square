package com.example.movieapptask.presentation.home

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapptask.R
import com.example.movieapptask.data.data_source.remote.dto.Genre
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.presentation.ui.theme.Axiforma
import com.example.movieapptask.presentation.ui.theme.SearchColor
import com.example.movieapptask.utils.UIState
import com.google.gson.Gson

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var searchText by remember { mutableStateOf("") }
    val genreState by viewModel.genreState.collectAsState()
    val movieState by viewModel.movieState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.search_for_a_content),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontFamily = Axiforma,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            placeholder = {
                Text(
                    text = "Search for a content.",
                    fontSize = 12.sp,
                    fontFamily = Axiforma,
                    fontWeight = FontWeight.Normal,
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SearchColor,
                unfocusedContainerColor = SearchColor,
                disabledContainerColor = SearchColor,
                focusedBorderColor = SearchColor,
                unfocusedBorderColor = SearchColor,
            ),
            shape = RoundedCornerShape(24.dp),
            enabled = false
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Categories",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontFamily = Axiforma,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))


        GenreState(genreState, viewModel::fetchMoviesByGenre)

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Most Searched",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontFamily = Axiforma,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        MovieState(movieState, navController)

    }

}

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
                val movieJson = Uri.encode(Gson().toJson(movie))
                navController.navigate("details_screen/$movieJson")
            }
    ) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
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


@Composable
private fun GenreState(
    genreState: UIState<List<Genre>>,
    onGenreClick: (String) -> Unit
) {
    when (genreState) {
        is UIState.Loading -> CircularProgressIndicator()
        is UIState.Success -> GenreChipsList(genreState.data, onGenreClick)
        is UIState.Error -> Toast.makeText(
            LocalContext.current,
            genreState.message,
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun GenreChipsList(genres: List<Genre>, onGenreClick: (String) -> Unit) {
    LazyRow {
        items(genres) { genre ->
            GenreChip(genre) {
                onGenreClick(genre.id.toString())
            }
        }
    }
}

@Composable
fun GenreChip(genre: Genre, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = genre.name,
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}