package com.example.test_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = SacoOrange,
    primaryVariant = SacoRed,
    secondary = SacoDarkGrey,
    surface = Surface,
    onSurface = SacoBlack,
    background = Background
)

private val LightColorPalette = lightColors(
    primary = SacoOrange,
    primaryVariant = SacoRed,
    secondary = SacoDarkGrey,
    surface = Surface,
    onSurface = SacoBlack,
    background = Background
)

@Composable
fun TestappTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
