package com.example.currentweather.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.currentweather.data.local.EntityMapper
import com.example.currentweather.data.local.RoomDao
import com.example.currentweather.data.remote.ApiCall
import com.example.currentweather.data.remote.Resource
import com.example.currentweather.data.remote.api.WeatherService
import com.example.currentweather.data.remote.model.mappers.imperial.ImperialMapper
import com.example.currentweather.data.remote.model.mappers.metric.MetricMapper
import com.example.currentweather.domain.model.weather.Weather
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
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
            Log.i(TAG, "getMetricWeatherDetails: Loading Resource")
            val weatherResponseResource = safeApiCall {
                Log.i(TAG, "getMetricWeatherDetails: Calling")
                weatherService.getForecast(params = params)
            }


            emit(
                Resource.transform(weatherResponseResource) { weatherForecastResponse ->
                    Log.i(
                        TAG,
                        "getMetricWeatherDetails: raw data ${weatherForecastResponse.apiForecast}"
                    )
                    metricMapper.map(weatherForecastResponse)
                })


        }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getImperialWeatherDetails(params: String): Flow<Resource<Weather>> =
        flow {

            val weatherResponseResource = safeApiCall {
                weatherService.getForecast(params = params)
            }



            emit(Resource.transform(weatherResponseResource) { weatherForecastResponse ->
                imperialMapper.map(weatherForecastResponse)
            })


        }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun loadAllWeatherLocations(unitSystem: UnitSystem): Flow<List<Resource<Weather>>> =
        flow {
            val weatherLocations = withContext(Dispatchers.IO) {
                weatherDao.getAllWeatherLocations()
                    .firstOrNull() // Get first non-empty emission or null if still empty
            }
            if (weatherLocations.isNullOrEmpty()) {
                Log.i(TAG, "No weather locations available to load.")
                emit(emptyList()) // Emit an empty list if no locations are found
            } else {
                coroutineScope {
                    val deferredLocations = weatherLocations.map { location ->
                        async {
                            if (unitSystem == UnitSystem.IMPERIAL) {
                                Log.i(
                                    TAG,
                                    "Load All Weather Locations: location: ${location.location}"
                                )
                                getImperialWeatherDetails(location.location).first()
                            } else {
                                getMetricWeatherDetails(location.location).first()
                            }
                        }
                    }
                    // Await all async results in parallel and emit the list of loaded locations
                    val loadedLocations = deferredLocations.awaitAll()
                    emit(loadedLocations)
                }
            }
        }.flowOn(Dispatchers.IO)


    suspend fun saveWeatherLocation(weatherLocation: String) =
        weatherDao.addWeatherLocation(EntityMapper().map(weatherLocation))

    suspend fun deleteAll() = weatherDao.deleteAll()

    suspend fun deleteWeatherLocation(weatherLocation: String) =
        weatherDao.deleteWeatherLocation(weatherLocation)


}