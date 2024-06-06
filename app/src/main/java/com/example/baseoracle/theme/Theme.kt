package com.example.baseoracle.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightColorPalette = lightColors(
    primary = ahorrobusPrimary,
    primaryVariant = ahorrobusPrimary,
    secondary = ahorrobusPrimary)

@Composable
fun BaseOracleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    statusBarColorEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = lightColorPalette
    val view = LocalView.current
    if (!view.isInEditMode && statusBarColorEnabled) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}
