    package com.commandiron.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = MyPrimaryColor,
    onPrimary = MyOnPrimaryColor,
    primaryContainer = MyPrimaryContainerColor,
    onPrimaryContainer = MyOnPrimaryContainerColor,
    tertiary = MyTertiaryColor,
    onTertiary = MyOnTertiaryColor,
    background = MyBackgroundColor,
    onBackground = MyOnBackgroundColor
)

private val LightColorPalette = lightColorScheme(
    primary = MyPrimaryColor,
    onPrimary = MyOnPrimaryColor,
    primaryContainer = MyPrimaryContainerColor,
    onPrimaryContainer = MyOnPrimaryContainerColor,
    tertiary = MyTertiaryColor,
    onTertiary = MyOnTertiaryColor,
    background = MyBackgroundColor,
    onBackground = MyOnBackgroundColor
)

@Composable
fun SefimTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleAlpha(
            draggedAlpha = 0.0f,
            focusedAlpha = 0.0f,
            hoveredAlpha = 0.0f,
            pressedAlpha = 0.0f
        )
}