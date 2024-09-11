package com.example.currentweather.ui.viewmodel.events

import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem

sealed class MainWeatherEvent() {
    data class FetchWeather(val params: String, val unitSystem: UnitSystem) : MainWeatherEvent()
}
