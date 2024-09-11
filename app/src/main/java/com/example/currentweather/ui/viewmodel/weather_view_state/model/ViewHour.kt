package com.example.currentweather.ui.viewmodel.weather_view_state.model

data class ViewHour(
    val time: String = "",
    val temperature: Double = 0.0,
    val condition: ViewCondition = ViewCondition()
)
