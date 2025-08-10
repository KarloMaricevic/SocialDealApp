package com.karlomaricevic.socialdeal.core.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Shimmer(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition("")
    val placeHolderEndColor = Color.Gray.copy(alpha = 0.2f)
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Gray,
        targetValue = placeHolderEndColor,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        label = "",
    )
    Box(modifier.background(animatedColor))
}
