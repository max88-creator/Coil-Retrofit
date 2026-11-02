package com.example.coilretrofit.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val RickMortyDarkColorScheme = darkColorScheme(
    primary = PortalGreen,
    onPrimary = SpaceDark,
    primaryContainer = PortalGreenDark,
    onPrimaryContainer = AcidGreen,
    secondary = RickBlue,
    onSecondary = SpaceDark,
    secondaryContainer = RickBlueDark,
    onSecondaryContainer = RickBlue,
    tertiary = MortyYellow,
    onTertiary = SpaceDark,
    background = SpaceDark,
    onBackground = Color(0xFFE8EDF5),
    surface = SpaceDarkSurface,
    onSurface = Color(0xFFE8EDF5),
    surfaceVariant = SpaceDarkElevated,
    onSurfaceVariant = Color(0xFFB8C4D9),
    outline = PortalGreenDark,
)

private val RickMortyLightColorScheme = lightColorScheme(
    primary = PortalGreenDark,
    onPrimary = Color.White,
    primaryContainer = AcidGreen,
    onPrimaryContainer = SpaceDark,
    secondary = RickBlueDark,
    onSecondary = Color.White,
    secondaryContainer = RickBlue,
    onSecondaryContainer = SpaceDark,
    tertiary = MortyYellow,
    onTertiary = SpaceDark,
    background = Color(0xFFF4F7FB),
    onBackground = SpaceDark,
    surface = Color.White,
    onSurface = SpaceDark,
    surfaceVariant = Color(0xFFE8EDF5),
    onSurfaceVariant = Color(0xFF4A5568),
    outline = PortalGreenDark,
)

@Composable
fun CoilRetrofitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) RickMortyDarkColorScheme else RickMortyLightColorScheme
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