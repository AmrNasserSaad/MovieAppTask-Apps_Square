package com.example.movieapptask.domain.use_case

import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.domain.repository.MovieRepository
import com.example.movieapptask.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<UIState<Movie>> = flow {
        try {
            emit(UIState.Loading)
            val movieDetails = movieRepository.getMovieDetailsById(movieId = movieId)
            emit(UIState.Success(movieDetails))

        } catch (e: HttpException) {
            emit(UIState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(UIState.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}