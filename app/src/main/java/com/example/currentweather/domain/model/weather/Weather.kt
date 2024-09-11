package com.example.currentweather.domain.model.weather

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)