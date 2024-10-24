package com.example.movieapptask.data.data_source.remote


import com.example.movieapptask.data.data_source.remote.dto.GenreResponse
import com.example.movieapptask.data.data_source.remote.dto.MovieResponse
import com.example.movieapptask.utils.Const.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = API_KEY
    ): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") genreIds: String
    ): MovieResponse

}
