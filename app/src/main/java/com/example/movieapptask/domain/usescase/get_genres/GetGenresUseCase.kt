package com.example.movieapptask.domain.usescase.get_genres

import com.example.movieapptask.data.data_source.remote.dto.Genres
import com.example.movieapptask.domain.repository.GenresRepository
import com.example.movieapptask.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: GenresRepository
) {
    operator fun invoke(): Flow<Resource<Genres>> = flow {
        try {
            emit(Resource.Loading())
            val genre = repository.getGenreList()
            emit(Resource.Success(genre))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }

    }
}