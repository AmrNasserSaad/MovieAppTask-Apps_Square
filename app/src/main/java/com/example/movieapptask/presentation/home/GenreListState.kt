package com.example.movieapptask.presentation.home

import com.example.movieapptask.data.data_source.remote.dto.Genre

data class GenreListState(

    val isLoading: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val error: String = ""

)
