package com.example.currentweather.domain.model.weather


data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val moonPhase: String,
    val moonIllumination: Int,
    val isMoonUp: Boolean,
    val isSunUp: Boolean
    )
