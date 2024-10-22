package com.example.movieapptask.data.repository

import com.example.movieapptask.data.data_source.remote.MovieApiService
import com.example.movieapptask.data.data_source.remote.dto.Genres
import com.example.movieapptask.domain.repository.GenresRepository
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val api: MovieApiService
) : GenresRepository {

    override suspend fun getGenreList(): Genres {
        return api.getGenreList()
    }
}