package com.example.currentweather.domain.model.weather

data class Current(
    val temperature: Double,
    val feelsLike: Double,
    val wind: Double,
    val windDegree:Int,
    val pressure: Double,
    val precipitation: Double,
    val humidity: Int,
    val cloud: Int,
    val dewPoint: Double,
    val heatIndex: Double,
    val visibility: Double,
    val gust: Double,
    val uv:Double,
    val condition: Condition
)