package com.example.movieapptask.domain.repository

import com.example.movieapptask.data.data_source.remote.dto.Genre
import com.example.movieapptask.data.data_source.remote.dto.Movie

interface MovieRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getMoviesByGenre(genreId: Int): List<Movie>
}


