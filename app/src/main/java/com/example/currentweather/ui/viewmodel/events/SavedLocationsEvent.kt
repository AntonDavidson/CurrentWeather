package com.example.currentweather.ui.viewmodel.events

sealed class SavedLocationsEvent {
    data class RemoveLocation(val locationName: String) : SavedLocationsEvent()
    data object LoadSavedLocations : SavedLocationsEvent()
}
