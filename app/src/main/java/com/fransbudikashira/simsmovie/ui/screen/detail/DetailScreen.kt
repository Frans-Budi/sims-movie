package com.fransbudikashira.simsmovie.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fransbudikashira.simsmovie.R
import com.fransbudikashira.core.domain.model.Movie
import com.fransbudikashira.simsmovie.ui.common.UiState
import com.fransbudikashira.simsmovie.ui.component.ButtonWithIcon
import com.fransbudikashira.simsmovie.ui.component.NetworkImage
import com.fransbudikashira.simsmovie.ui.component.ToggleableCircleButton
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme

@Composable
fun DetailScreen(
    movieId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getMovieById(movieId)
    }

    when(uiState) {
        is UiState.Loading -> { LoadingContent() }
        is UiState.Success -> {
            val movie = (uiState as UiState.Success<Movie>).data
            DetailContent(
                movie = movie,
                onClickFavorite = { newValue ->
                    viewModel.setFavoriteMovie(movie, newValue)
                },
                modifier = modifier
            )
        }
        is UiState.Error -> {}
    }

}

@Composable
fun DetailContent(
    movie: Movie,
    onClickFavorite: (Boolean) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            NetworkImage(
                imageUrl = movie.poster,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(RectangleShape)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = movie.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = stringResource(R.string.sub_title_detail, movie.year, movie.type),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                ButtonWithIcon(
                    text = stringResource(R.string.watch_the_movie_now),
                    icon = Icons.Default.PlayArrow,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.size(12.dp))
                ToggleableCircleButton(
                    onClick = onClickFavorite,
                    isToggle = movie.isFavorite,
                    iconOff = Icons.Default.FavoriteBorder,
                    iconOn = Icons.Default.Favorite,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    SimsMovieTheme {
        DetailContent(
            movie = Movie(
                imdbID = "tt10919420",
                poster = "https://m.media-amazon.com/images/M/MV5BMTI5MDU3MTYyMF5BMl5BanBnXkFtZTYwODgyODc3._V1_SX300.jpg",
                title = "A Street Cat Named Bob",
                year = "2021",
                type = "Movie",
                isFavorite = false
            ),
            onClickFavorite = {},
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.background
            )
        )
    }
}