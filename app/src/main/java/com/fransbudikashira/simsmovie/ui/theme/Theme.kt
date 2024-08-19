package com.fransbudikashira.simsmovie.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Orange20,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    secondaryContainer = Orange20, // Active Menu Background
    onSecondaryContainer = Orange40, // Active Menu Icon
    onSurface = Orange40, // Active Menu Title Text
    onSurfaceVariant = DarkGrey50, // InActive Menu Title Text and Icon
    background = DarkGrey70,
    onBackground = LightGrey40,
    surfaceContainer = DarkGrey60,
    surfaceTint = DarkGrey50,
    errorContainer = red20
)

private val LightColorScheme = lightColorScheme(
    primary = Orange20,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    secondaryContainer = Orange20, // Active Menu Background
    onSecondaryContainer = Orange40, // Active Menu Icon
    onSurface = Orange40, // Active Menu Title Text
    onSurfaceVariant = DarkGrey50, // InActive Menu Title Text and Icon
    background = DarkGrey70,
    onBackground = LightGrey40,
    surfaceContainer = DarkGrey60,
    surfaceTint = DarkGrey50,
    errorContainer = red20

    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    */
)

@Composable
fun SimsMovieTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}