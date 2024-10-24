package com.example.movieapptask.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapptask.data.data_source.remote.dto.Genre
import com.example.movieapptask.data.data_source.remote.dto.Movie
import com.example.movieapptask.domain.use_case.GetGenreListUseCase
import com.example.movieapptask.domain.use_case.GetMoviesByGenreUseCase
import com.example.movieapptask.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val getGenreListUseCase: GetGenreListUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase

) : ViewModel() {

    private val _genreState = MutableStateFlow<UIState<List<Genre>>>(UIState.Loading)
    val genreState: StateFlow<UIState<List<Genre>>> = _genreState

    private val _movieState = MutableStateFlow<UIState<List<Movie>>>(UIState.Loading)
    val movieState: StateFlow<UIState<List<Movie>>> = _movieState

    init {
        fetchGenres()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            getGenreListUseCase().collect { result ->
                _genreState.value = result
            }
        }
    }

    fun fetchMoviesByGenre(genreId: String) {
        viewModelScope.launch {
            getMoviesByGenreUseCase(genreId).collect { result ->
                _movieState.value = result
            }
        }
    }


}