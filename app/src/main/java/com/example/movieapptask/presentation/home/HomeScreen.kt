package com.example.movieapptask.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapptask.R
import com.example.movieapptask.data.data_source.remote.dto.Genre
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.presentation.home.components.GenreState
import com.example.movieapptask.presentation.home.components.MovieState
import com.example.movieapptask.presentation.ui.theme.SearchColor
import com.example.movieapptask.presentation.ui.theme.titleSmallStyle
import com.example.movieapptask.utils.UIState

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val searchText by remember { mutableStateOf("") }
    val genreState by viewModel.genreState.collectAsState()
    val movieState by viewModel.movieState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchSection(searchText)

        Spacer(modifier = Modifier.height(28.dp))

        GenreSection(genreState, viewModel)

        Spacer(modifier = Modifier.height(28.dp))

        MovieListSection(movieState, navController)

    }

}

@Composable
fun SearchSection(searchText: String) {
    var searchText1 = searchText
    Text(
        text = stringResource(R.string.search_for_a_content),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        style = titleSmallStyle
    )
    Spacer(modifier = Modifier.height(8.dp))


    OutlinedTextField(
        value = searchText1,
        onValueChange = {
            searchText1 = it
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_for_a_content),
                style = titleSmallStyle.copy(color = Color.Gray)
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
}

@Composable
private fun GenreSection(
    genreState: UIState<List<Genre>>,
    viewModel: HomeViewModel
) {
    Text(
        text = stringResource(R.string.categories),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        style = titleSmallStyle
    )
    Spacer(modifier = Modifier.height(8.dp))

    GenreState(genreState, viewModel::fetchMoviesByGenre)
}

@Composable
private fun MovieListSection(
    movieState: UIState<List<Movie>>,
    navController: NavController
) {
    Text(
        text = stringResource(R.string.most_searched),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        style = titleSmallStyle
    )
    Spacer(modifier = Modifier.height(16.dp))

    MovieState(movieState, navController)
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}