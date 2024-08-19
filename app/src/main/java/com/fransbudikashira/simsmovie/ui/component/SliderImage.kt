package com.fransbudikashira.simsmovie.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SliderImage(
    sliderImages: List<Int>,
    pagerState: PagerState,
    scope: CoroutineScope,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .wrapContentSize()
        ) { currentPage ->
            val scale = animateFloatAsState(
                targetValue = if (pagerState.currentPage == currentPage) 1.0f else 0.9f
            )

            Card(
                elevation = CardDefaults.cardElevation(),
                modifier = modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
            ) {
                Image(
                    painter = painterResource(sliderImages[currentPage]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }
        }
        IconButton(
            onClick = {
                val nextPage = pagerState.currentPage + 1
                if (nextPage < sliderImages.size) {
                    scope.launch {
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            },
            colors = IconButtonDefaults.iconButtonColors().copy(
                contentColor = Color.LightGray,
                containerColor = Color.DarkGray.copy(alpha = 0.4f)
            ),
            modifier = modifier
                .padding(20.dp)
                .size(42.dp)
                .align(Alignment.CenterEnd)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier =  modifier.fillMaxSize()
            )
        }
        IconButton(
            onClick = {
                val prevPage = pagerState.currentPage - 1
                if (prevPage >= 0) {
                    scope.launch {
                        pagerState.animateScrollToPage(prevPage)
                    }
                }
            },
            colors = IconButtonDefaults.iconButtonColors().copy(
                contentColor = Color.LightGray,
                containerColor = Color.DarkGray.copy(alpha = 0.4f)
            ),
            modifier = modifier
                .padding(20.dp)
                .size(42.dp)
                .align(Alignment.CenterStart)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier =  modifier.fillMaxSize()
            )
        }
        PageIndicator(
            pageCount = sliderImages.size,
            currentPage = pagerState.currentPage,
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        )
    }
}

@Composable
private fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorDot(
                isSelected = it == currentPage,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun IndicatorDot(
    isSelected: Boolean,
    modifier: Modifier
) {
    val size = animateDpAsState(
        targetValue = if (isSelected) 10.dp else 8.dp,
        label = ""
    )
    Box(
        modifier = modifier
            .padding(3.dp)
            .size(size.value)
            .clip(CircleShape)
            .background(
                if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    Color.LightGray.copy(alpha = 0.7f)
            )
    )
}