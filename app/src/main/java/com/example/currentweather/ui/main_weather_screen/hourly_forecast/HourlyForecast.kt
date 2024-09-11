package com.example.currentweather.ui.main_weather_screen.hourly_forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.components.common.ContentCard
import com.example.currentweather.ui.components.common.ForecastTitle
import com.example.currentweather.ui.image_manager.LoadImageFromNetwork
import com.example.currentweather.ui.theme.CurrentWeatherTheme
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewHour
import com.example.currentweather.util.hourFormat

@Composable
fun HourlyForecastContent(
    hoursList: List<ViewHour>,
    modifier: Modifier = Modifier
) {
    ContentCard(modifier) {
        Column {
            ForecastTitle("Daily ApiForecastDay")
            HorizontalDivider()
            LazyRow(Modifier.padding(start = 16.dp, end = 16.dp)) {
                items(hoursList) { item ->
                    HourColumn(item)
                }
            }
        }
    }
}


@Composable
fun HourColumn(hour: ViewHour) {
    Column(
        Modifier
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Text(
            text = "${hour.temperature.toInt()}",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )


        LoadImageFromNetwork(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .size(34.dp), imageUrl = hour.condition.icon
        )
        Text(
            text = hourFormat(hour.time),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
fun DailyForecastPreview() {
    CurrentWeatherTheme {
        HourlyForecastContent(
            hoursList = ViewWeatherMapper().mapToView(Dummy.imperialDummy)
                .forecastWeather.forecastDays[0].hourly
        )
    }
}      