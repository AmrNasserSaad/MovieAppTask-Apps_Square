package com.example.movieapptask.presentation.home.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.movieapptask.data.data_source.remote.dto.Genre
import com.example.movieapptask.utils.UIState

@Composable
fun GenreState(
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