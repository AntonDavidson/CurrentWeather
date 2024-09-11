package com.example.currentweather.ui.image_manager

import androidx.compose.runtime.Composable
import com.example.currentweather.ui.animation.DayToSunsetBackGround
import com.example.currentweather.ui.animation.NightToSunriseBackground

@Composable
fun BackgroundImage(hourOfTheDay: Int, isRaining: Boolean = false) {
    when (hourOfTheDay) {
        in 5..7 -> NightToSunriseBackground(switchToSunrise = true)
        in 7..17 -> DayToSunsetBackGround(sunset = false, raining = isRaining)
        in 18..19 -> DayToSunsetBackGround(sunset = true, raining = isRaining)
        in 20..24 -> NightToSunriseBackground(switchToSunrise = false)
        else -> NightToSunriseBackground(switchToSunrise = false)
    }}