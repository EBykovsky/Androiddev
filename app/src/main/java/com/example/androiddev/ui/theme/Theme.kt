package com.example.androiddev.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FarmColorScheme = lightColorScheme(
    primary = FarmBrown,
    onPrimary = Color.White,

    primaryContainer = FarmCream,
    onPrimaryContainer = FarmBrownDark,

    secondary = FarmBrownDark,
    onSecondary = Color.White,

    secondaryContainer = FarmCreamLight,
    onSecondaryContainer = FarmBrownDark,

    tertiary = FarmBullGreen,
    onTertiary = FarmBrownDark,

    background = FarmCreamLight,
    onBackground = FarmBrownDark,

    surface = FarmCream,
    onSurface = FarmBrownDark,

    surfaceVariant = FarmCreamLight,
    onSurfaceVariant = FarmBrownDark,

    outline = FarmBorder
)

@Composable
fun AndroidDevTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FarmColorScheme,
        typography = Typography,
        content = content
    )
}