package com.example.movieapptask.data.data_source.remote.dto


data class Genre(
    val id: Int,
    val name: String
)

data class Genres(
    val genres: List<Genre>
)