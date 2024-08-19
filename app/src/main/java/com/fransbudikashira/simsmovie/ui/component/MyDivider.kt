package com.fransbudikashira.simsmovie.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyDivider(
    modifier: Modifier
) {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.4f),
        modifier = modifier
    )
}