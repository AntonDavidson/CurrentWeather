package com.example.currentweather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currentweather.data.remote.Resource
import com.example.currentweather.domain.usecases.LoadSavedLocations
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.SavedLocationsState
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SavedLocationsViewModel"

class SavedLocationsViewModel @Inject constructor(
    private val loadSavedLocations: LoadSavedLocations,
    val viewWeatherMapper: ViewWeatherMapper
) :
    ViewModel() {
    private val weatherState = MutableStateFlow(SavedLocationsState())
    val weather: StateFlow<SavedLocationsState> = weatherState

    fun loadLocations(unitSystem: UnitSystem) {
        viewModelScope.launch(Dispatchers.IO) {
            loadSavedLocations(unitSystem)
                .onStart {
                    // Emit the loading state at the beginning of the flow
                    weatherState.value = weatherState.value.copy(loading = true)
                }
                .catch { exception ->

                    weatherState.value = weatherState.value.copy(
                        loading = false,
                        errorMessage = exception.message ?: "Unknown Error"
                    )
                }
                .collect { results ->
                    val viewWeatherData = results.map { result ->
                        when (result) {
                            is Resource.Loading -> {
                                Log.i(TAG, "Loading Data : Resource Loading")
                            }

                            is Resource.Success -> {
                                viewWeatherMapper.mapToView(result.data)
                            }

                            is Resource.Error -> {

                            }
                        }
                    }

                    weatherState.value = weatherState.value.copy(
                        loading = false,
                        locations = viewWeatherData.filterIsInstance<ViewWeather>(),
                        unitSystem = unitSystem,
                        errorMessage = ""
                    )
                }
        }
    }
}
