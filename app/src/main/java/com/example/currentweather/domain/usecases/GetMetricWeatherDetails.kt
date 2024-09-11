package com.example.currentweather.domain.usecases

import android.util.Log
import com.example.currentweather.data.remote.Resource
import com.example.currentweather.data.repository.WeatherRepository
import com.example.currentweather.domain.model.weather.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
private const val TAG="GetMetricWeatherDetails"

class GetMetricWeatherDetails @Inject constructor(private val weatherRepository: WeatherRepository) {
     operator fun invoke(params: String): Flow<Resource<Weather>> {
        Log.i(TAG, "invoke: Weather Data")

        return weatherRepository.getMetricWeatherDetails(params = params)
    }
}