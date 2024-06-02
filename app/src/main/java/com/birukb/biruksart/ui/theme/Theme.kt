package com.birukb.biruksart.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF006064),  // Dark teal for primary elements
    secondary = Color(0xFF4DB6AC),  // Complementary soft teal for secondary elements
    background = Color(0xFF303030),  // Darker shade for the background
    surface = Color(0xFF424242),  // Surface color for cards, sheets, etc.
    onPrimary = Color.White,  // Text color on primary
    onSecondary = Color.Black,  // Text color on secondary
    onBackground = Color.White,  // Text color on background
    onSurface = Color.White  // Text color on surface
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00838F),  // Brighter teal for primary elements
    secondary = Color(0xFF80CBC4),  // Lighter teal for secondary elements
    background = Color(0xFFE0E0E0),  // Lighter shade for the background
    surface = Color(0xFFF0F0F0),  // Surface color for cards, sheets, etc.
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun BiruksArtTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,  // Use dynamic color based on device theme on Android 12+
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = colorScheme.primary.luminance() < 0.5
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
