package com.example.currentweather.ui.viewmodel.weather_view_state

import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewWeather

data class WeatherViewState(
    val loading: Boolean? = null,
    val unitSystem: UnitSystem? = null,
    val weather: ViewWeather = ViewWeather(),
    val errorMessage: String? = null
)
