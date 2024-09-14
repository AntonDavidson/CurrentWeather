package com.example.currentweather.domain.usecases

import com.example.currentweather.data.repository.WeatherRepository
import javax.inject.Inject

class RemoveLocation @Inject constructor(private val weatherRepository: WeatherRepository){
    suspend operator fun invoke(location:String){
        weatherRepository.deleteWeatherLocation(location)
    }
}