package com.example.currentweather.ui.main_weather_screen.current_weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.components.common.ContentCard
import com.example.currentweather.ui.image_manager.LoadImageFromNetwork
import com.example.currentweather.ui.theme.CurrentWeatherTheme
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewWeather
import com.example.currentweather.util.hourAndMinuteFormat

@Composable
fun CurrentWeatherContent(
    weather: ViewWeather,
    modifier: Modifier = Modifier
) {
    ContentCard(modifier) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LocationInfo(modifier = Modifier.weight(1f), weather = weather)
            CurrentCondition(Modifier.weight(0.4f), weather = weather)
        }
    }
}

@Composable
fun CurrentCondition(modifier: Modifier = Modifier, weather: ViewWeather) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LoadImageFromNetwork(
                modifier = Modifier.size(100.dp),
                imageUrl = weather.currentWeather.condition.icon
            )
            Text(
                text = weather.currentWeather.condition.text,
                style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Composable
fun LocationInfo(modifier: Modifier = Modifier, weather: ViewWeather) {
    Column(modifier = modifier) {
        HighAndLowTemp(weather)
        LocationName(weather)
        LocationTime(weather)
        LocationTemp(weather)
        LocationFeelsLikeTemp(weather)
        LocationChanceOfPrecipt(weather)
    }
}

@Composable
fun LocationChanceOfPrecipt(weather: ViewWeather) {
    Text(
        text = "Chance of precipitation ${
            weather.forecastWeather.forecastDays[0].day.totalPrecip
                .toInt()
        }%",
        style = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun LocationFeelsLikeTemp(weather: ViewWeather) {
    Text(
        text = "${weather.currentWeather.feelsLike.toInt()}°C",
        style = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun LocationTemp(weather: ViewWeather) {
    Text(
        text = weather.currentWeather.temperature.toString() + "°C",
        style = MaterialTheme.typography.displayLarge.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )
}

@Composable
fun LocationTime(weather: ViewWeather) {
    Text(
        text = hourAndMinuteFormat(weather.location.localtime),
        style = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun LocationName(weather: ViewWeather) {
    Text(
        text = weather.location.name,
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun HighAndLowTemp(weather: ViewWeather) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "High ${weather.forecastWeather.forecastDays[0].day.maxTemp.toInt()}°C",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        Divider(
            modifier = Modifier
                .padding(4.dp)
                .height(15.dp)
                .width(1.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = "Low ${weather.forecastWeather.forecastDays[0].day.minTemp.toInt()}°C",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun CurrentWeatherPreview() {
    CurrentWeatherTheme {
        CurrentWeatherContent(
            ViewWeatherMapper().mapToView(Dummy.imperialDummy)
        )
    }
}