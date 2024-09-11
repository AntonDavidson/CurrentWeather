package com.example.currentweather.ui.viewmodel.weather_view_state.model

data class ViewCurrent(
    val temperature: Double = 0.0,
    val feelsLike: Double = 0.0,
    val wind: Double = 0.0,
    val windSpeed: String = "",
    val windDegree: Int = 0,
    val pressure: Double = 0.0,
    val precipitation: Double = 0.0,
    val humidity: Int = 0,
    val dewPoint: Double = 0.0,
    val visibility: Double = 0.0,
    val uv: Double = 0.0,
    val condition: ViewCondition = ViewCondition()
)
