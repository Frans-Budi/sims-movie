package com.fransbudikashira.simsmovie.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fransbudikashira.simsmovie.R
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme
import com.fransbudikashira.simsmovie.ui.theme.poppinsFontFamily

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    ProfileContent(
        name = "Frans Budi Kashira",
        email = "fransbudikashira@gmail.com",
        image = R.drawable.frans,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    name: String,
    email: String,
    image: Int,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Spacer(modifier = modifier.padding(16.dp))
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(180.dp)
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    shape = CircleShape
                )
                .clip(CircleShape)
        )
        Spacer(modifier = modifier.padding(6.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = modifier.padding(2.dp))
        Text(
            text = email,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFontFamily
            ),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SimsMovieTheme {
        ProfileContent(
            name = "Frans Budi Kashira",
            email = "fransbudikashira@gmail.com",
            image = R.drawable.frans,
            modifier = Modifier
        )
    }
}