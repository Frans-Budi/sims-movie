package com.fransbudikashira.simsmovie.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme

@Composable
fun ToggleableCircleButton(
    onClick: (Boolean) -> Unit,
    isToggle: Boolean,
    iconOff: ImageVector,
    iconOn: ImageVector,
    modifier: Modifier = Modifier
) {
    val icon = if (isToggle) iconOn else iconOff
    IconButton(
        onClick = { onClick(!isToggle) },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
        ),
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Preview
@Composable
fun ToggleableCircleButtonPreview() {
    SimsMovieTheme {
        ToggleableCircleButton(
            onClick = {},
            iconOff = Icons.Default.FavoriteBorder,
            iconOn = Icons.Default.Favorite,
            isToggle = false,
            modifier = Modifier
        )
    }
}