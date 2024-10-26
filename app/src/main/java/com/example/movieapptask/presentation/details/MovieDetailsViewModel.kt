package com.example.movieapptask.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.domain.use_case.GetMovieDetailsByIdUseCase
import com.example.movieapptask.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(

    private val getMovieDetailsByIdUseCase: GetMovieDetailsByIdUseCase,

    ) : ViewModel() {


    private val _movieDetailsState = MutableStateFlow<UIState<Movie>>(UIState.Loading)
    val movieDetailsState: StateFlow<UIState<Movie>> = _movieDetailsState


    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsByIdUseCase(movieId).collect { result ->
                _movieDetailsState.value = result
            }
        }
    }
}