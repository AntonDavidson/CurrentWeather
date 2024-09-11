package com.example.currentweather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currentweather.data.remote.Resource
import com.example.currentweather.domain.usecases.GetImperialWeatherDetails
import com.example.currentweather.domain.usecases.GetMetricWeatherDetails
import com.example.currentweather.ui.viewmodel.events.DialogEvent
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DialogViewModel"

class DialogViewModel @Inject constructor(
    private val getImperialWeatherDetails: GetImperialWeatherDetails,
    private val getMetricWeatherDetails: GetMetricWeatherDetails,
    private val viewMapper: ViewWeatherMapper
) : ViewModel() {

    private val weatherState = MutableStateFlow(WeatherViewState())
    val weather: StateFlow<WeatherViewState> = weatherState

    fun onDialogEvent(dialogEvent: DialogEvent) {
        Log.i(TAG, "onDialogEvent: Started")
        when (dialogEvent) {
            is DialogEvent.FetchWeather -> {

                if (dialogEvent.unitSystem == UnitSystem.IMPERIAL) {
                    Log.i(TAG, "onDialogEvent: Loading Imperial Data")
                    loadImperialWeatherData(dialogEvent.params)

                } else if (dialogEvent.unitSystem == UnitSystem.METRIC) {
                    Log.i(TAG, "onDialogEvent: Loading Metric Data")
                    loadMetricWeatherData(dialogEvent.params)

                }
            }

            is DialogEvent.SaveLocation -> {

            }
        }
    }

    private fun loadImperialWeatherData(params: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(TAG, "loadImperialWeatherData: Started")
            when (val result = getImperialWeatherDetails.invoke(params)) {
                is Resource.Loading -> {
                    Log.i(TAG, "loadImperialWeatherData: Resource Loading")
                    weatherState.emit(weatherState.value.copy(loading = true))
                }

                is Resource.Success -> {
                    // Update the state with weather data and stop loading
                    Log.i(TAG, "loadImperialWeatherData: Data Successfully Loaded")
                    weatherState.emit(
                        weatherState.value.copy(
                            loading = false,
                            unitSystem = UnitSystem.IMPERIAL,
                            weather = viewMapper.mapToView(result.data)
                        )
                    )
                }

                is Resource.Error -> {
                    // Update the state with the error message and stop loading
                    Log.i(TAG, "loadImperialWeatherData: Failed To Load Data")
                    weatherState.emit(
                        weatherState.value.copy(
                            loading = false,
                            errorMessage = result.error
                        )
                    )
                }
            }
        }
    }

    private fun loadMetricWeatherData(params: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMetricWeatherDetails(params)
                .onStart {
                    // Emit the loading state at the beginning of the flow
                    Log.i(TAG, "loadMetricWeatherData: Resource Loading")
                    weatherState.emit(weatherState.value.copy(loading = true))
                }
                .catch { exception ->
                    // Handle any exception that occurs during the flow collection
                    Log.e(TAG, "loadMetricWeatherData: Failed with exception", exception)
                    weatherState.emit(
                        weatherState.value.copy(
                            loading = false,
                            errorMessage = exception.message ?: "Unknown Error"
                        )
                    )
                }
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            // Normally, you don't need this case because `onStart` handles it
                            Log.i(TAG, "loadMetricWeatherData: Resource Loading")
                        }

                        is Resource.Success -> {
                            // Update the state with weather data and stop loading
                            Log.i(TAG, "loadMetricWeatherData: Data Successfully Loaded")
                            Log.i(TAG, "loadMetricWeatherData: before map ${result.data.forecast}")
                            weatherState.emit(
                                weatherState.value.copy(
                                    loading = false,
                                    unitSystem = UnitSystem.METRIC,
                                    weather = viewMapper.mapToView(result.data)
                                )
                            )
                        }

                        is Resource.Error -> {
                            // Update the state with the error message and stop loading
                            Log.i(TAG, "loadMetricWeatherData: Failed To Load Data")
                            weatherState.emit(
                                weatherState.value.copy(
                                    loading = false,
                                    errorMessage = result.error ?: "Unknown Error"
                                )
                            )
                        }
                    }
                }
        }
    }


}