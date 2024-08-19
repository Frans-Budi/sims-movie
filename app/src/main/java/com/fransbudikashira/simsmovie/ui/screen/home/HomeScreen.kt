package com.fransbudikashira.simsmovie.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fransbudikashira.simsmovie.R
import com.fransbudikashira.core.domain.model.Movie
import com.fransbudikashira.simsmovie.ui.common.UiState
import com.fransbudikashira.simsmovie.ui.component.MovieItem
import com.fransbudikashira.simsmovie.ui.component.SliderImage
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClick: (Movie) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val sliderImages = listOf(
        R.drawable.avengers,
        R.drawable.pemimpi,
        R.drawable.shang_chi
    )
    val pagerState = rememberPagerState {
        sliderImages.size
    }
    val scope = rememberCoroutineScope()

    HomeContent(
        sliderImages = sliderImages,
        pagerState = pagerState,
        scope = scope,
        modifier = modifier
    ) {
        val popularMovies by viewModel.popularMovies.collectAsState()
        val forYouMovies by viewModel.forYouMovies.collectAsState()

        when(popularMovies) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                HomeSection(
                    title = stringResource(R.string.popular),
                    movies = (popularMovies as UiState.Success<List<Movie>>).data,
                    modifier = Modifier.wrapContentSize()
                ) { movies ->
                    LazyRow(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier =  modifier.fillMaxWidth()
                    ) {
                        items(movies, key = { it.imdbID }) { movie ->
                            MovieItem(
                                imageUrl = movie.poster,
                                onClick = { onClick(movie) },
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(160.dp)
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {}
        }
        Spacer(modifier = Modifier.size(10.dp))
        when(forYouMovies) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                HomeSection(
                    title = stringResource(R.string.for_you),
                    movies = (forYouMovies as UiState.Success<List<Movie>>).data,
                    modifier = Modifier.wrapContentSize()
                ) { movies ->
                    LazyRow(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = modifier.fillMaxWidth()
                    ) {
                        items(movies, key = { it.imdbID }) { movie ->
                            MovieItem(
                                imageUrl = movie.poster,
                                onClick = { onClick(movie) },
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(160.dp)
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    sliderImages: List<Int>,
    pagerState: PagerState,
    scope: CoroutineScope,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            val nextPage = (pagerState.currentPage + 1) % sliderImages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            SliderImage(
                sliderImages = sliderImages,
                pagerState = pagerState,
                scope = scope,
                modifier = Modifier
            )
            content()
        }
    }
}

@Composable
private fun HomeSection(
    title: String,
    modifier: Modifier,
    movies: List<Movie>,
    content: @Composable (List<Movie>) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(6.dp))
        content(movies)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SimsMovieTheme {}
}