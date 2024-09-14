package com.example.currentweather.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currentweather.ui.main_weather_screen.WeatherMainScreen
import com.example.currentweather.ui.saved_locations.SavedLocationsScreen
import com.example.currentweather.ui.viewmodel.events.DialogEvent
import com.example.currentweather.ui.viewmodel.events.MainWeatherEvent
import com.example.currentweather.ui.viewmodel.events.SavedLocationsEvent
import com.example.currentweather.ui.viewmodel.weather_view_state.SavedLocationsState
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState

@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewEvent: (MainWeatherEvent) -> Unit,
    dialogEvent: (DialogEvent) -> Unit,
    savedLocationsEvent: (SavedLocationsEvent) -> Unit,
    savedLocations: SavedLocationsState,
    dialogWeather: WeatherViewState,
    weatherState: WeatherViewState,
    unitSystem: UnitSystem
) {
    NavHost(navController = navController, startDestination = Rout.HomeScreen.screenRoute) {
        composable(Rout.SavedLocationsScreen.screenRoute) {
            SavedLocationsScreen(
                modifier = Modifier.padding(paddingValues),
                savedLocationsEvent = savedLocationsEvent,
                dialogEvent=dialogEvent,
                dialogWeatherViewState = dialogWeather,
                savedLocationsViewState = savedLocations
            )
        }
        composable(Rout.HomeScreen.screenRoute) {
            WeatherMainScreen(
                modifier = Modifier.padding(paddingValues),
                viewState = weatherState,
                unitSystem = unitSystem,
                viewEvent = viewEvent
            )
        }
    }
}