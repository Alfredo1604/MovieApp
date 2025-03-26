package com.example.movieapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = NeonBlue500,
    onPrimary = Color.White,
    primaryContainer = NeonBlue700,
    onPrimaryContainer = NeonBlue100,

    secondary = CornflowerBlue500,
    onSecondary = Color.White,
    secondaryContainer = CornflowerBlue700,
    onSecondaryContainer = CornflowerBlue100,

    tertiary = JordyBlue500,
    onTertiary = Color.White,
    tertiaryContainer = JordyBlue700,
    onTertiaryContainer = JordyBlue100,

    background = Color.Black,
    onBackground = Color.White,
    surface = NeonBlue900,
    onSurface = LightCyan500,
    surfaceVariant = Periwinkle700,
    onSurfaceVariant = Periwinkle100,

    error = Periwinkle300,
    onError = Color.Black,
    errorContainer = CornflowerBlue400,
    onErrorContainer = Color.White,

    inversePrimary = NeonBlue200,
    inverseSurface = CornflowerBlue800,
    inverseOnSurface = LightCyan500,

    outline = NeonBlue600,
    outlineVariant = NeonBlue300,
    scrim = NeonBlue100,
    surfaceTint = NeonBlue400
)

private val LightColorScheme = lightColorScheme(
    primary = NeonBlue400,
    onPrimary = Color.Black,
    primaryContainer = NeonBlue200,
    onPrimaryContainer = NeonBlue100,

    secondary = CornflowerBlue400,
    onSecondary = Color.Black,
    secondaryContainer = CornflowerBlue200,
    onSecondaryContainer = CornflowerBlue100,

    tertiary = JordyBlue400,
    onTertiary = Color.Black,
    tertiaryContainer = JordyBlue200,
    onTertiaryContainer = JordyBlue100,

    background = Color.White,
    onBackground = Color.Black,
    surface = NeonBlue100,
    onSurface = Color.Black,
    surfaceVariant = Periwinkle200,
    onSurfaceVariant = Periwinkle800,

    error = Periwinkle300,
    onError = Color.White,
    errorContainer = CornflowerBlue100,
    onErrorContainer = Color.Black,

    inversePrimary = NeonBlue600,
    inverseSurface = CornflowerBlue100,
    inverseOnSurface = NeonBlue800,

    outline = NeonBlue500,
    outlineVariant = NeonBlue300,
    scrim = LightCyan100,
    surfaceTint = NeonBlue700
)

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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
