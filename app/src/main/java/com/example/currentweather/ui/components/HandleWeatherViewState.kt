package com.example.currentweather.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.currentweather.ui.error.ErrorContent

@Composable
fun HandleWeatherViewState(
    content: @Composable () -> Unit,
    viewState: Boolean,
    error: String,
) {
    when (viewState) {
        true -> {
            CircularProgressIndicator()
        }

        false -> {
            if (error.isNotEmpty()) {
                ErrorContent(onSearch = {


                })
            } else {
                content()
            }
        }
    }
}