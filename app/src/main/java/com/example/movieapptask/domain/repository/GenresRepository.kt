package com.example.movieapptask.domain.repository

import com.example.movieapptask.data.data_source.remote.dto.Genres

interface GenresRepository {

    suspend fun getGenreList(): Genres
}