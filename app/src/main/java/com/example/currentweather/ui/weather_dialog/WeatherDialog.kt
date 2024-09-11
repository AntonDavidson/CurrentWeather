package com.example.currentweather.ui.weather_dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.main_weather_screen.MainWeatherContent
import com.example.currentweather.ui.viewmodel.events.MainWeatherEvent
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState

@Composable
fun WeatherDialog(
    viewEvent: (MainWeatherEvent) -> Unit,
    weatherViewState: WeatherViewState,
    onSaveLocation: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        MainWeatherContent(
            viewState = weatherViewState,
        )
    }
}

@Preview
@Composable
fun WeatherDialogPreview() {
    WeatherDialog(
        viewEvent = {},
        weatherViewState = WeatherViewState(
            weather = ViewWeatherMapper().mapToView(
                Dummy.imperialDummy
            )
        ), onSaveLocation = {}, onDismiss = {}
    )
}