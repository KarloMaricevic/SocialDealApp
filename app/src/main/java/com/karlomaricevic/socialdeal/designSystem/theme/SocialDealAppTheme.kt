package com.karlomaricevic.socialdeal.designSystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.graphics.drawable.toDrawable

private val colorScheme = lightColorScheme(
    primary = blue,
    surface = white,
    background = gray300,
    error = red,
    onBackground = black,
    onSurface = black,
)

@Composable
fun SocialDealAppTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window.apply {
                statusBarColor = colorScheme.surface.toArgb()
                setBackgroundDrawable(colorScheme.background.toArgb().toDrawable())
            }
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = Typography,
    )
}
