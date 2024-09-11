package com.example.currentweather.navigation

sealed class Rout(
    val screenRoute: String,
) {
    data object HomeScreen:Rout(screenRoute = "home_screen")
    data object SavedLocationsScreen:Rout(screenRoute = "saved_locations_screen")
}
