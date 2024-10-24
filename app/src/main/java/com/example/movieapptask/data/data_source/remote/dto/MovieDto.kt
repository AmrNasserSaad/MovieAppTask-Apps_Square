package com.example.movieapptask.data.data_source.remote.dto

data class GenreResponse(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String,
    val vote_average: Double,
    val genre_ids: List<Int>
)

