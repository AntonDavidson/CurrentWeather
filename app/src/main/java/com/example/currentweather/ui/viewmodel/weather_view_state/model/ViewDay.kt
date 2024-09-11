package com.example.currentweather.ui.viewmodel.weather_view_state.model

data class ViewDay(
    val maxTemp: Double,
    val minTemp: Double,
    val maxWind: Double,
    val totalPrecip: Double,
    val condition: ViewCondition,
)
