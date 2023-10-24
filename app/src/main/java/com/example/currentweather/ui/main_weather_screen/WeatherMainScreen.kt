package com.example.currentweather.ui.main_weather_screen


import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.currentweather.data.location.getCurrentLocation
import com.example.currentweather.ui.error.ErrorContent
import com.example.currentweather.ui.image_manager.BackgroundImage
import com.example.currentweather.ui.permissions.LocationPermissionRequestDialog
import com.example.currentweather.ui.viewmodel.events.MainWeatherEvent
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState
import com.example.currentweather.util.currentMillisToHours
import com.example.currentweather.util.removeTrailingSpace

private const val TAG = "MainScreen"

@Composable
fun WeatherMainScreen(
    viewState: WeatherViewState, unitSystem: UnitSystem, viewEvent: (event: MainWeatherEvent) -> Unit
) {
    val context = LocalContext.current
    val isLocationPermissionRequested = remember { mutableStateOf(false) }

    if (!isLocationPermissionRequested.value) {
        LocationPermissionRequestDialog(onDismissed = {
            MainWeatherEvent.FetchWeather("london", unitSystem)
            isLocationPermissionRequested.value = true
        }, onPermissionGranted = {
            LaunchedEffect(isLocationPermissionRequested.value) {
                viewEvent(MainWeatherEvent.FetchWeather(getCurrentLocation(context), unitSystem))
                isLocationPermissionRequested.value = true
            }
        })


    }


    val hourOfTheDay = remember {
        mutableIntStateOf(currentMillisToHours(System.currentTimeMillis()))
    }
    val isRaining = remember {
        mutableStateOf(false)
    }
    BackgroundImage(hourOfTheDay = hourOfTheDay.intValue, isRaining.value)




    when (viewState.loading) {
        true -> {
            CircularProgressIndicator()
        }

        false -> {
            if (viewState.errorMessage != null) {
                ErrorContent(onSearch = {
                    viewEvent(
                        MainWeatherEvent.FetchWeather(
                            removeTrailingSpace(it.lowercase()),
                            UnitSystem.METRIC
                        )
                    )
                })
            } else {
                MainWeatherContent(
                    viewState = viewState,
                )
            }
        }

        null -> {
            CircularProgressIndicator()
        }
    }
}








