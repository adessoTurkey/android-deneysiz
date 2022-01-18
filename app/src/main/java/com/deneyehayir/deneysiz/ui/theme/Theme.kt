package com.deneyehayir.deneysiz.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = White0,
    primaryVariant = White0,
    secondary = Orange,
    surface = White0
)

@Composable
fun DeneysizTheme(content: @Composable() () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
