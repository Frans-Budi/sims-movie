package com.fransbudikashira.simsmovie.ui.screen.favorite

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fransbudikashira.core.domain.model.Movie
import com.fransbudikashira.simsmovie.ui.common.UiState
import com.fransbudikashira.simsmovie.ui.component.MovieItem
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onClick: (Movie) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    Log.d("HALO", "FavoriteScreen: $state")
    when(state) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            val movies = (state as UiState.Success<List<Movie>>).data
            FavoriteContent(
                movies = movies,
                onClick = onClick,
                modifier = modifier
            )
        }
        is UiState.Error -> {
            val message = (state as UiState.Error).errorMessage
            FavoriteError(
                message = message,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun FavoriteContent(
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(movies, key = { it.imdbID }) { item ->
            MovieItem(
                imageUrl = item.poster,
                onClick = { onClick(item) },
                modifier = Modifier
            )
        }
    }
}

@Composable
fun FavoriteError(
    message: String,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(message)
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    SimsMovieTheme {
        FavoriteContent(
            movies = listOf(
                Movie(
                    imdbID = "tt10919420",
                    poster = "https://m.media-amazon.com/images/M/MV5BMTI5MDU3MTYyMF5BMl5BanBnXkFtZTYwODgyODc3._V1_SX300.jpg",
                    title = "A Street Cat Named Bob",
                    year = "2021",
                    type = "Movie",
                    isFavorite = false
                ),
                Movie(
                    imdbID = "tt10919421",
                    poster = "https://m.media-amazon.com/images/M/MV5BMTI5MDU3MTYyMF5BMl5BanBnXkFtZTYwODgyODc3._V1_SX300.jpg",
                    title = "A Street Cat Named Bob",
                    year = "2021",
                    type = "Movie",
                    isFavorite = false
                ),
                Movie(
                    imdbID = "tt10919422",
                    poster = "https://m.media-amazon.com/images/M/MV5BMTI5MDU3MTYyMF5BMl5BanBnXkFtZTYwODgyODc3._V1_SX300.jpg",
                    title = "A Street Cat Named Bob",
                    year = "2021",
                    type = "Movie",
                    isFavorite = false
                )
            ),
            onClick = {},
            modifier =  Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}