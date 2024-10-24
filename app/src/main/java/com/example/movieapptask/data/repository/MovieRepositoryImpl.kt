package com.example.movieapptask.data.repository

import com.example.movieapptask.data.data_source.remote.MovieApi
import com.example.movieapptask.data.data_source.remote.dto.Genre
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun getGenres(): List<Genre> = movieApi.getGenres().genres

    override suspend fun getMoviesByGenre(genreId: Int): List<Movie> = movieApi.getMoviesByGenre(genreIds = genreId.toString()).results

}
