package org.umn.utsmap.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = EarthyBrown,
    onPrimary = Color.White,
    primaryContainer = EarthyBrownLight,
    secondary = MutedGreen,
    onSecondary = Color.White,
    background = SurfaceWarm,
    surface = CardBackground,
    onSurface = TextPrimary,
    outline = OutlineBorder
)

@Composable
fun KostellaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}