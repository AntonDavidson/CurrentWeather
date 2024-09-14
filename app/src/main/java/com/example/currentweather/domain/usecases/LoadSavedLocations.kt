package com.example.currentweather.domain.usecases

import android.util.Log
import com.example.currentweather.data.remote.Resource
import com.example.currentweather.data.repository.WeatherRepository
import com.example.currentweather.domain.model.weather.Weather
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
private const val TAG="LoadSavedLocations"
class LoadSavedLocations @Inject constructor(private val weatherRepository: WeatherRepository) {

   suspend operator fun invoke(unitSystem: UnitSystem): Flow<List<Resource<Weather>>> {
        Log.i(TAG, "invoke: load saved locations been called")
        return weatherRepository.loadAllWeatherLocations(unitSystem)
    }
}