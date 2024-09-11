package com.example.currentweather.domain.model.weather

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val localtime: String
)
