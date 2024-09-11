package com.example.currentweather.ui.main_weather_screen.uv_index.uvsettings


import androidx.compose.ui.graphics.Color
import com.example.currentweather.R


val uvIndexColors = listOf(
    Color(0xFF00FF00),    // Green (Low 0-2)
    Color(0xFFFFFF99),    // Light Yellow (Low Moderate 2-3)
    Color(0xFFFFFF00),    // Yellow (Moderate 3-4)
    Color(0xFFFFD700),    // Gold (Moderate 4-5)
    Color(0xFFFFA500),    // Orange (Moderate High 5-6)
    Color(0xFFFF8C00),    // Dark Orange (High Moderate 6-7)
    Color(0xFFFF0000),    // Red (High 7-8)
    Color(0xFFFF1493),    // Pink (Very High 8-9)
    Color(0xFF8A2BE2),    // Violet (Very High 9-10)
    Color(0xFF8B0000)     // Dark Red (Extreme 11+)
)

fun Int.uvIndexStringRes(): Int {
    return when (this) {
        in 0..2 -> R.string.low
        in 3..5 -> R.string.moderate
        in 6..7 -> R.string.high
        in 8..10 -> R.string.very_high
        else -> R.string.low
    }
}

fun Int.uvIndexAlertStringRes(): Int {
    return when (this) {
        in 0..2 -> R.string.low_uv_alert
        in 3..5 -> R.string.moderate_uv_alert
        in 6..7 -> R.string.high_uv_alert
        in 8..10 -> R.string.very_high_uv_alert
        else -> R.string.low_uv_alert
    }
}