package com.example.currentweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currentweather.ui.main_weather_screen.WeatherMainScreen
import com.example.currentweather.ui.saved_locations.SavedLocationsScreen
import com.example.currentweather.ui.viewmodel.events.MainWeatherEvent
import com.example.currentweather.ui.viewmodel.weather_view_state.SavedLocationsState
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState

@Composable
fun NavigationHost(
    navController: NavHostController,
    viewEvent: (MainWeatherEvent) -> Unit,
    savedLocations: SavedLocationsState,
    dialogWeather: WeatherViewState,
    weatherState: WeatherViewState,
    unitSystem: UnitSystem
) {
    NavHost(navController = navController, startDestination = Rout.HomeScreen.screenRoute) {
        composable(Rout.SavedLocationsScreen.screenRoute) {
            SavedLocationsScreen(
                viewEvent = viewEvent,
                dialogWeatherViewState = dialogWeather,
                savedLocationsViewState = savedLocations
            )
        }
        composable(Rout.HomeScreen.screenRoute) {
            WeatherMainScreen(viewState = weatherState, unitSystem = unitSystem, viewEvent = viewEvent)
        }
    }
}