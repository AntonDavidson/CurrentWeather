package com.example.currentweather.domain.model.weather

data class ForecastDay(
    val date: String,
    val dateEpoch: Int,
    val day: Day,
    val astro: Astro,
    val hourly: List<Hour>
)
