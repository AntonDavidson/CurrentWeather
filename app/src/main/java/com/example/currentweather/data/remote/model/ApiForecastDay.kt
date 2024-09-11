package com.example.currentweather.data.remote.model

import com.google.gson.annotations.SerializedName


data class ApiForecastDay(
    @SerializedName("date") var date: String? = null,
    @SerializedName("date_epoch") var dateEpoch: Int? = null,
    @SerializedName("day") var day: ApiDay? = ApiDay(),
    @SerializedName("astro") var astro: ApiAstro? = ApiAstro(),
    @SerializedName("hour") var hour: ArrayList<ApiHour> = arrayListOf()
)