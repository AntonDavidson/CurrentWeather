package com.example.currentweather.data.remote.api

import com.example.currentweather.BuildConfig
import com.example.currentweather.data.remote.model.ApiWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
//    @GET("apiCurrent.json")
//    suspend fun getWeather(
//        @Query("key") key: String = BuildConfig.API_KEY,
//        @Query("q") params: String
//    ): Response<CurrentWeatherResponse>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") params: String,
        @Query("days") days: Int = 7
    ): Response<ApiWeatherResponse>
}
//http://api.weatherapi.com/v1/forecast.json?key=e007aff51ccb4e1e97e111437230108&q=London&days=1