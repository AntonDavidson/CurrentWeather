package com.example.currentweather.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiForecast(@SerializedName("forecastday") var forecastDay: ArrayList<ApiForecastDay> = arrayListOf())
