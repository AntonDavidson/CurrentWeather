package com.example.currentweather.ui.viewmodel.weather_view_state.model

data class ViewForecastDay(
    val date: String = "",
    val day: ViewDay,
    val astro: ViewAstro,
    val hourly: List<ViewHour>
)
