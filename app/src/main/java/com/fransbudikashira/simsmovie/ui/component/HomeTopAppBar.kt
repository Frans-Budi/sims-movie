package com.fransbudikashira.simsmovie.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fransbudikashira.simsmovie.R
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme
import com.fransbudikashira.simsmovie.ui.theme.kaushanScriptFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier,
    onClickSearchButton: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(TopAppBarDefaults.windowInsets)
            .padding(horizontal = 24.dp)
            .padding(top = 10.dp)
    ) {
        TopBarLogo()
        IconButton(
            onClick = onClickSearchButton,
            colors = IconButtonDefaults.iconButtonColors().copy(
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun TopBarLogo(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.popcorn),
            contentDescription = stringResource(R.string.logo),
            modifier = modifier.size(32.dp)
        )
        Spacer(modifier = modifier.size(8.dp))
        Text(
            text = stringResource(R.string.app_name).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = kaushanScriptFontFamily,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview()
@Composable
fun HomeTopAppBarPreview() {
    SimsMovieTheme {
        HomeTopAppBar(
            modifier = Modifier,
            onClickSearchButton = {}
        )
    }
}
