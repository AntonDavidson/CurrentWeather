package com.example.currentweather.ui.weather_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.error.ErrorContent
import com.example.currentweather.ui.main_weather_screen.MainWeatherContent
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState

@Composable
fun WeatherDialog(
    weatherViewState: WeatherViewState,
    onSaveLocation: () -> Unit,
    onDismiss: () -> Unit
) {
    val showSaveButton = remember {
        mutableStateOf(false)
    }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column {
            TextButton(onClick = {
                onSaveLocation()
                onDismiss()
            }) {
                Text(text = "Save")
            }
            when (weatherViewState.loading) {
                true -> {
                    CircularProgressIndicator()
                }

                false -> {
                    if (weatherViewState.errorMessage != null) {
                        ErrorContent(onSearch = {


                        })
                    } else {
                        showSaveButton.value = true
                        MainWeatherContent(viewState = weatherViewState)
                    }
                }

                else -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherDialogPreview() {
    WeatherDialog(
        weatherViewState = WeatherViewState(
            weather = ViewWeatherMapper().mapToView(
                Dummy.imperialDummy
            )
        ), onSaveLocation = {}, onDismiss = {}
    )
}