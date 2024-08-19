package com.fransbudikashira.simsmovie.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fransbudikashira.simsmovie.R
import com.fransbudikashira.simsmovie.ui.navigation.Screen
import com.fransbudikashira.simsmovie.ui.theme.SimsMovieTheme
import com.fransbudikashira.simsmovie.ui.theme.kaushanScriptFontFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    SplashContent(modifier)
}

@Composable
private fun SplashContent(
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.popcorn),
                contentDescription = stringResource(R.string.logo),
                modifier.size(160.dp)
            )
            Text(
                text = stringResource(R.string.app_name).uppercase(),
                fontFamily = kaushanScriptFontFamily,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SimsMovieTheme {
        SplashContent(Modifier)
    }
}