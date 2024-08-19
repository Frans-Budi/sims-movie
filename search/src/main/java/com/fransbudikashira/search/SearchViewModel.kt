package com.fransbudikashira.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
class SearchViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _searchResult: MutableStateFlow<UiState<List<Movie>>> =
        MutableStateFlow(UiState.Success(emptyList()))
    val searchResult: StateFlow<UiState<List<Movie>>> get() = _searchResult

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun searchMovie(keyword: String) {
        viewModelScope.launch {
            movieUseCase.searchMovie(keyword)
                .catch {
                    _searchResult.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _searchResult.value = when(result) {
                        is Resource.Loading -> UiState.Loading
                        is Resource.Success -> UiState.Success(result.data ?: emptyList())
                        is Resource.Error -> UiState.Error(result.message.toString())
                    }
                }
        }
    }
}