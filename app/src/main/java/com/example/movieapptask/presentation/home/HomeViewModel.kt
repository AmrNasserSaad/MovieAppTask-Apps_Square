package com.example.movieapptask.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movieapptask.domain.usescase.get_genres.GetGenresUseCase
import com.example.movieapptask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    private val _genreState = mutableStateOf(GenreListState())
    val genreState: State<GenreListState> = _genreState

    init {
        getGenres()
    }

    private fun getGenres() {
        getGenresUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _genreState.value = GenreListState(genres = result.data?.genres ?: emptyList())
                }

                is Resource.Error -> {
                    _genreState.value =
                        GenreListState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _genreState.value = GenreListState(isLoading = true)
                }
            }

        }
    }


}