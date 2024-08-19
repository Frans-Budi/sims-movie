package com.fransbudikashira.simsmovie.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fransbudikashira.core.domain.model.Movie
import com.fransbudikashira.core.domain.usecase.MovieUseCase
import com.fransbudikashira.simsmovie.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val _state: MutableStateFlow<UiState<Movie>> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState<Movie>> get() = _state

    fun getMovieById(movieId: String) {
        viewModelScope.launch {
            movieUseCase.getMovieById(movieId)
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect { movie ->
                    _state.value = UiState.Success(movie)
                }
        }
    }

    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}