package com.example.currentweather.ui.viewmodel.weather_view_state

import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewWeather

data class SavedLocationsState(
    val loading: Boolean = true,
    val locations: List<ViewWeather> = listOf(),
    val unitSystem: UnitSystem = UnitSystem.IMPERIAL,
    val errorMessage: String = ""
)