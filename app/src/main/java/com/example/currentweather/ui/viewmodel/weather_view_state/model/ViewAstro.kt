package com.example.currentweather.ui.viewmodel.weather_view_state.model

data class ViewAstro(
    val sunrise: String = "",
    val sunset: String = "",
    val isMoonUp: Boolean = false,
    val isSunUp: Boolean = false
)
