package com.example.currentweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.currentweather.ui.image_manager.LoadImageFromNetwork
import com.example.currentweather.ui.saved_locations.SearchBar
import com.example.currentweather.ui.theme.BlackBackground
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewWeather


@Composable
fun TopBar(
    weather: ViewWeather,
    textValue: MutableState<String>,
    onSearch: (params: String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = BlackBackground.copy(0.1f),
                shape = CircleShape.copy(CornerSize(25.dp))
            )

    ) {
        LoadImageFromNetwork(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.TopEnd),
            imageUrl = weather.currentWeather.condition.icon,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (weather.location.name),
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = weather.currentWeather.condition.text,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            )


            SearchBar(modifier = Modifier.padding(top = 5.dp, bottom = 15.dp),
                value = textValue.value,
                onValueChanged = {
                    textValue.value = it
                },
                onSearchActionClick = {
                    onSearch(textValue.value)
                })

        }
    }
}