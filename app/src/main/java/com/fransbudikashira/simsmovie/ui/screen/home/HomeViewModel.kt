package com.fransbudikashira.simsmovie.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fransbudikashira.core.data.Resource
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
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val _popularMovies: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val popularMovies: StateFlow<UiState<List<Movie>>> get() = _popularMovies

    private val _forYouMovies: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val forYouMovies: StateFlow<UiState<List<Movie>>> get() = _forYouMovies

    init {
        getPopularMovies()
        getForYouMovies()
    }

    private fun getPopularMovies(keyword: String = "avengers") {
        viewModelScope.launch {
            movieUseCase.searchMovie(keyword)
                .catch {
                    _popularMovies.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _popularMovies.value = when(result) {
                        is Resource.Loading -> UiState.Loading
                        is Resource.Success -> UiState.Success(result.data ?: emptyList())
                        is Resource.Error -> UiState.Error(result.message.toString())
                    }
                }
        }
    }

    private fun getForYouMovies(keyword: String = "baby") {
        viewModelScope.launch {
            movieUseCase.searchMovie(keyword)
                .catch {
                    _forYouMovies.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _forYouMovies.value = when(result) {
                        is Resource.Loading -> UiState.Loading
                        is Resource.Success -> UiState.Success(result.data ?: emptyList())
                        is Resource.Error -> UiState.Error(result.message.toString())
                    }
                }
        }
    }
}