package com.example.currentweather.ui.saved_locations

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.components.SwipeToDeleteContainer
import com.example.currentweather.ui.components.common.ContentCard
import com.example.currentweather.ui.image_manager.BackgroundImage
import com.example.currentweather.ui.viewmodel.events.DialogEvent
import com.example.currentweather.ui.viewmodel.events.SavedLocationsEvent
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.SavedLocationsState
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewWeather
import com.example.currentweather.ui.weather_dialog.WeatherDialog
import com.example.currentweather.util.extractHourFromTimeEpochString

private const val TAG = "SavedLocationsScreen"

@Composable
fun SavedLocationsScreen(
    modifier: Modifier = Modifier,
    savedLocationsEvent: (SavedLocationsEvent) -> Unit,
    dialogEvent: (DialogEvent) -> Unit,
    dialogWeatherViewState: WeatherViewState,
    savedLocationsViewState: SavedLocationsState
) {
    val searchValue = remember {
        mutableStateOf("")
    }
    val showDialog = remember {
        mutableStateOf(false)
    }
    val loadLocations = remember {
        mutableStateOf(true)
    }
    if (loadLocations.value) {
        savedLocationsEvent(SavedLocationsEvent.LoadSavedLocations)
        loadLocations.value = false
    }



    LazyColumn(modifier = modifier, contentPadding = PaddingValues(16.dp), content = {
        item {
            SearchBar(
                value = searchValue.value,
                onValueChanged = { searchValue.value = it },
                onSearchActionClick = {
                    dialogEvent(DialogEvent.FetchWeather(searchValue.value, UnitSystem.METRIC))
                    showDialog.value = true

                })
        }
        items(savedLocationsViewState.locations) { location ->
            Spacer(modifier = Modifier.height(20.dp))
            SwipeToDeleteContainer(
                item = location,
                onDelete = {
                    savedLocationsEvent(
                        SavedLocationsEvent.RemoveLocation(
                            location.location.name.lowercase().replaceFirstChar { it.uppercase() })
                    )
                }) {
                SavedLocation(viewCurrentLocationWeather = location)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    })
    if (showDialog.value) {
        WeatherDialog(
            weatherViewState = dialogWeatherViewState,
            onSaveLocation = {
                Log.i(TAG, "SavedLocationsScreen: saving ${searchValue.value}")
                dialogEvent(
                    DialogEvent.SaveLocation(
                        locationName = searchValue.value.lowercase()
                            .replaceFirstChar { it.uppercase() })
                )
                savedLocationsEvent(SavedLocationsEvent.LoadSavedLocations)
            }) {
            showDialog.value = false
        }
    }

}

@Composable
fun SavedLocation(viewCurrentLocationWeather: ViewWeather) {

    ContentCard(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        BackgroundImage(hourOfTheDay = extractHourFromTimeEpochString(viewCurrentLocationWeather.location.localtime))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(text = viewCurrentLocationWeather.location.name)
                Text(text = viewCurrentLocationWeather.location.region)
            }
            Text(
                text = viewCurrentLocationWeather.currentWeather.condition.text,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = viewCurrentLocationWeather.currentWeather.temperature.toString(),
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Text(text = "H:19C L:10C", modifier = Modifier.align(Alignment.BottomEnd))

        }
    }
}

@Composable
@Preview(showBackground = true)
fun LocationsScreenPreview() {
    val viewStates = mutableListOf<ViewWeather>()

    for (i in 0..10) {
        viewStates +=
            ViewWeatherMapper().mapToView(
                Dummy.imperialDummy.copy(
                    location = Dummy.imperialDummy.location.copy(
                        localtime = "2024-12-12 ${
                            String.format("%02d", (5 + (i * 2)) % 24)
                        }:00"
                    )
                )

            )
    }
    SavedLocationsScreen(
        dialogEvent = {},
        savedLocationsEvent = {},
        dialogWeatherViewState = WeatherViewState(weather = ViewWeatherMapper().mapToView(Dummy.imperialDummy)),
        savedLocationsViewState =
        SavedLocationsState(
            loading = false,
            unitSystem = UnitSystem.IMPERIAL,
            locations = viewStates.toList()
        ),

        )
}