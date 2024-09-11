package com.example.currentweather.ui.viewmodel.weather_view_state.model

data class ViewWeather(
    val location: ViewLocation = ViewLocation(),
    val currentWeather: ViewCurrent = ViewCurrent(),
    val forecastWeather: ViewForecast = ViewForecast()
)
