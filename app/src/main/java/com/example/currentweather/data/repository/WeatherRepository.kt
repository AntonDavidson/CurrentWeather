package com.example.currentweather.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.currentweather.data.local.RoomDao
import com.example.currentweather.data.local.entity.WeatherEntity
import com.example.currentweather.data.remote.ApiCall
import com.example.currentweather.data.remote.Resource
import com.example.currentweather.data.remote.api.WeatherService
import com.example.currentweather.data.remote.model.mappers.imperial.ImperialMapper
import com.example.currentweather.data.remote.model.mappers.metric.MetricMapper
import com.example.currentweather.domain.model.weather.Weather
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val TAG = "WeatherRepository"

class
WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDao: RoomDao,
    private val metricMapper: MetricMapper,
    private val imperialMapper: ImperialMapper
) :
    ApiCall() {
    @WorkerThread
    fun getMetricWeatherDetails(params: String): Flow<Resource<Weather>> =
        flow {
            emit(Resource.Loading())
            Log.i(TAG, "getMetricWeatherDetails: Loading Resource")
            val weatherResponseResource = safeApiCall {
                Log.i(TAG, "getMetricWeatherDetails: Calling")
                weatherService.getForecast(params = params)
            }


            val weatherMetric =
                Resource.transform(weatherResponseResource) { weatherForecastResponse ->
                    Log.i(
                        TAG,
                        "getMetricWeatherDetails: raw data ${weatherForecastResponse.apiForecast}"
                    )
                    metricMapper.mapToDomain(weatherForecastResponse)
                }

            emit(weatherMetric)

        }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getImperialWeatherDetails(params: String): Flow<Resource<Weather>> =
        flow {
            emit(Resource.Loading())

            val weatherResponseResource = safeApiCall {
                weatherService.getForecast(params = params)
            }

            val weatherImperial =
                Resource.transform(weatherResponseResource) { weatherForecastResponse ->
                    imperialMapper.mapToDomain(weatherForecastResponse)
                }

            emit(weatherImperial)
        }.flowOn(Dispatchers.IO)

    fun loadAllWeatherLocations(unitSystem: UnitSystem): Flow<List<Resource<Weather>>> =
        flow {
            val weatherLocations = weatherDao.getAllWeatherLocations()
            val loadedLocations = mutableListOf<Resource<Weather>>()

            weatherLocations.last().forEach { location ->
                val weatherResource = if (unitSystem == UnitSystem.IMPERIAL) {
                    getImperialWeatherDetails(location.location).first()
                } else {
                    getMetricWeatherDetails(location.location).first()
                }
                loadedLocations.add(weatherResource)
            }
            emit(loadedLocations)
        }.flowOn(Dispatchers.IO)

    fun saveWeatherLocation(weatherLocation: WeatherEntity) =
        weatherDao.addWeatherLocation(weatherLocation)

    suspend fun deleteAll() = weatherDao.deleteAll()

    fun deleteWeatherLocation(weatherLocation: WeatherEntity) =
        weatherDao.deleteWeatherLocation(weatherLocation)

    fun getWeatherLocation(id: Long) = flow {
        val location = weatherDao.getWeatherLocation(id)
        emit(location)
    }.flowOn(Dispatchers.IO)
}