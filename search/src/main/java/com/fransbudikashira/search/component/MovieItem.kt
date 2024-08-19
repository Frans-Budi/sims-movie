package com.fransbudikashira.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun MovieItem(
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    NetworkImage(
        imageUrl = imageUrl,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() }
    )
}
