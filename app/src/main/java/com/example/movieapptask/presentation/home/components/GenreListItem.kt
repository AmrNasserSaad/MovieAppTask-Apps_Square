package com.example.movieapptask.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapptask.data.data_source.remote.dto.Genre

@Composable
fun GenreListItem(
    genre: Genre,
    onItemClick: (Genre) -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, Color.Black),
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onItemClick(genre)
            }
    ) {
        Text(
            text = genre.name,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = Color.Cyan
        )
    }

}