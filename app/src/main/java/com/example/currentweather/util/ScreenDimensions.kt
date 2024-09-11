package com.example.currentweather.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun screenHeightFraction(fraction: Float): Dp {
    val configuration = LocalConfiguration.current
    return (configuration.screenHeightDp * fraction).dp
}

@Composable
fun screenWidthFraction(fraction: Float): Dp {
    val configuration = LocalConfiguration.current
    return (configuration.screenWidthDp * fraction).dp
}