package com.example.movieapptask.data.data_source.remote

import com.example.movieapptask.BuildConfig.API_KEY
import com.example.movieapptask.data.data_source.remote.dto.Genres
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("genre/movie/list")
    suspend fun getGenreList( @Query("apiKey") apiKey: String = API_KEY): Genres
}