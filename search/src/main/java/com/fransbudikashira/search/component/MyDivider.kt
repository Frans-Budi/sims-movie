package com.fransbudikashira.search.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyDivider(
    modifier: Modifier
) {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.4f),
        modifier = modifier
    )
}