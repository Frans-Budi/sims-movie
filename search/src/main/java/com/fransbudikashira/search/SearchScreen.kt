package com.fransbudikashira.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fransbudikashira.core.domain.model.Movie
import com.fransbudikashira.search.common.UiState
import com.fransbudikashira.search.component.MovieItem
import com.fransbudikashira.search.component.MyDivider
import com.fransbudikashira.search.component.MySearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onClick: (Movie) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.query
    val searchResult by viewModel.searchResult.collectAsState()

    val focusRequester = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current

    // Request focus when SearchScreen is first composed
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    SearchContent(
        query = query,
        onQueryChange = viewModel::onQueryChange,
        onSearch = viewModel::searchMovie,
        focusRequester = focusRequester,
        onKeyboardAction = {
            Log.d("HALO", "SearchScreen: $focusRequester")
            localFocusManager.clearFocus()
        },
        modifier = Modifier
    ) {
        when(searchResult) {
            is UiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val data = (searchResult as UiState.Success<List<Movie>>).data
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(80.dp),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = modifier.fillMaxSize()
                ) {
                    items(data, key = { it.imdbID }) { item ->
                        MovieItem(
                            imageUrl = item.poster,
                            onClick = { onClick(item) },
                            modifier = Modifier
                        )
                    }
                }
            }
            is UiState.Error -> {
                val errorMessage = (searchResult as UiState.Error).errorMessage
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = errorMessage)
                }
            }
        }
    }
}

@Composable
fun SearchContent(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    focusRequester: FocusRequester,
    onKeyboardAction: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            MySearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                onKeyboardAction = onKeyboardAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )
            Spacer(modifier = Modifier.size(12.dp))
            MyDivider(modifier = Modifier)
            Spacer(modifier = Modifier.size(4.dp))
            content()
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    SearchScreen(
        onClick = {},
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    )
}