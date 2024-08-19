package com.fransbudikashira.simsmovie.ui.screen.favorite

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
class FavoriteViewModel @Inject constructor (
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val _state: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState<List<Movie>>> get() = _state

    init {
        getFavoriteMovie()
    }

    private fun getFavoriteMovie() {
        viewModelScope.launch {
            movieUseCase.getFavoriteMovie()
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect { movies ->
                    if(movies.isEmpty()) _state.value = UiState.Error("No favorite movies found.")
                    else _state.value = UiState.Success(movies)
                }
        }
    }
}