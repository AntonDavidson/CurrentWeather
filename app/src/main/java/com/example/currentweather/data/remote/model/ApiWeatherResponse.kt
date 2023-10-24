package com.example.currentweather.data.remote.model


import com.google.gson.annotations.SerializedName


data class ApiWeatherResponse(
    @SerializedName("location" ) var apiLocation : ApiLocation? = ApiLocation(),
    @SerializedName("current"  ) var apiCurrent  : ApiCurrent?  = ApiCurrent(),
    @SerializedName("forecast" ) var apiForecast : ApiForecast? = ApiForecast()
)