package com.fransbudikashira.simsmovie.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fransbudikashira.simsmovie.R
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onKeyboardAction: () -> Unit,
    modifier: Modifier,
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { keyword ->
            onSearch(keyword)
            onKeyboardAction()
        },
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                color = MaterialTheme.colorScheme.surfaceTint
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            inputFieldColors = TextFieldDefaults.textFieldColors().copy(
                focusedTextColor = Color.White,
                unfocusedTextColor = MaterialTheme.colorScheme.surfaceTint,
                cursorColor = Color.White,
                focusedLeadingIconColor = Color.White
            ),
        ),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 3.dp,
        modifier = modifier
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun MySearchBarPreview() {
    SimsMovieTheme {
        MySearchBar(
            query = "Laptop",
            onQueryChange = {},
            onSearch = {},
            onKeyboardAction = {},
            modifier = Modifier
        )
    }
}