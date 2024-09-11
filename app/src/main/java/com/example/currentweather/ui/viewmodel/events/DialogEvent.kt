package com.example.currentweather.ui.viewmodel.events

import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem

sealed class DialogEvent {
    data class FetchWeather(val params: String, val unitSystem: UnitSystem) :DialogEvent()
    data class SaveLocation(val locationName: String) : DialogEvent()
}