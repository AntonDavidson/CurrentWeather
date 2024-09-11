package com.example.currentweather.domain.model.weather

data class Day(
    val maxTemp: Double,
    val minTemp: Double,
    val avgTemp: Double,
    val maxWind: Double,
    val totalPrecip: Double,
    val totalSnow: Int,
    val avgVis: Double,
    val avgHumidity: Int,
    val dailyWillItRain: Int,
    val dailyChanceOfRain: Int,
    val dailyWillItSnow: Int,
    val dailyChanceOfSnow: Int,
    val condition: Condition,
    val uv: Double
)
