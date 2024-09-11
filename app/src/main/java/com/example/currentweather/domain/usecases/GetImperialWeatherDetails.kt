package com.example.currentweather.domain.usecases

import com.example.currentweather.data.remote.Resource
import com.example.currentweather.data.repository.WeatherRepository
import com.example.currentweather.domain.model.weather.Weather
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetImperialWeatherDetails @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun invoke(params: String): Resource<Weather> {
        return weatherRepository.getImperialWeatherDetails(params = params).first()
    }
}