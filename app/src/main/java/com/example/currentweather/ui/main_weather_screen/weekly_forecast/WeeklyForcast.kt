package com.example.currentweather.ui.main_weather_screen.weekly_forecast

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.components.common.ContentCard
import com.example.currentweather.ui.components.common.ForecastTitle
import com.example.currentweather.ui.image_manager.LoadImageFromNetwork
import com.example.currentweather.ui.main_weather_screen.hourly_forecast.HourColumn
import com.example.currentweather.ui.theme.CurrentWeatherTheme
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewDay
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewForecast
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewForecastDay

private const val TAG="WeeklyForecast"
private const val TODAY_INDEX = 0
private const val TOMORROW_INDEX = 1

@Composable
fun WeeklyForecast(
    forecast: ViewForecast, modifier: Modifier = Modifier
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    ContentCard(modifier) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ForecastTitle("Next ${forecast.forecastDays.size} Days ForecastDay")
                IconButton(
                    onClick = { isExpanded.value = !isExpanded.value },
                    modifier = Modifier.padding(12.dp)
                ) {
                    RotatingIcon(isExpanded.value)
                }
            }
            when (isExpanded.value) {
                true -> {
                    forecast.forecastDays.forEachIndexed { index, forecastDay ->
                        Day(forecastDay, index)
                    }
                }

                false -> {
                    Day(forecastDay = forecast.forecastDays[TODAY_INDEX], index = TODAY_INDEX)
                }
            }
        }
    }

}

@Composable
private fun RotatingIcon(rotate: Boolean) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (rotate) {
            180f
        } else {
            0f
        },
        animationSpec = tween(
            durationMillis = 500,
            easing = { it }
        ),
        label = "RotationAnimation"
    )
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .wrapContentSize()
            .rotate(rotationAngle)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
        )

    }
}

@Composable
private fun Day(forecastDay: ViewForecastDay, index: Int) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
    )
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                }
                .padding(
                    16.dp
                )) {

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DayDate(index, forecastDay)
                DayCondition(forecastDay.day)
            }
            DayInfo(forecastDay.day)
        }
        ExpandedCardContent(
            forecastDay = forecastDay, isExpanded = isExpanded
        )
    }
}

@Composable
private fun DayInfo(day: ViewDay) {
    Row {
        Column {
            LoadImageFromNetwork(
                modifier = Modifier.size(42.dp), imageUrl = day.condition.icon
            )
            Log.i(TAG, "DayInfo: icon : ${day.condition.icon} text ${day.condition.text}")
            Text(
                text = "${day.totalPrecip.toInt()}%",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

        }
        Column(Modifier.align(Alignment.Top)) {
            Text(
                text = "${day.maxTemp}C",
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "${day.minTemp}C",
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun DayCondition(forecastDay: ViewDay) {
    Text(
        text = forecastDay.condition.text,
        modifier = Modifier.padding(bottom = 10.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
private fun DayDate(
    index: Int, forecastDay: ViewForecastDay
) {
    Text(
        text = when (index) {
            TODAY_INDEX -> "Today"
            TOMORROW_INDEX -> "Tomorrow"
            else -> forecastDay.date
        },
        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Left),
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}


@Composable
fun ExpandedCardContent(
    forecastDay: ViewForecastDay, isExpanded: Boolean
) {

    AnimatedVisibility(visible = isExpanded) {
        Column(
            modifier = Modifier.padding(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            LazyRow {
                items(forecastDay.hourly) { item ->
                    HourColumn(item)
                }
            }
            DoubleRowText(firstRow = "Sunrise", secondRow = forecastDay.astro.sunrise)
            DoubleRowText(firstRow = "Sunset", secondRow = forecastDay.astro.sunset)
            DoubleRowText(
                firstRow = "WindKph", secondRow = forecastDay.day.maxWind.toInt().toString()
            )
            DoubleRowText(
                firstRow = "Precipitation", secondRow = "${forecastDay.day.totalPrecip}%"
            )
        }
    }
}

@Composable
private fun DoubleRowText(firstRow: String, secondRow: String) {
    Text(
        text = "${firstRow}\n${secondRow}", style = MaterialTheme.typography.titleSmall.copy(
            textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onPrimaryContainer
        ), modifier = Modifier.padding(2.dp)
    )
}

@Preview
@Composable
fun WeeklyForecastPreview() {
    CurrentWeatherTheme {
        WeeklyForecast(forecast = ViewWeatherMapper().mapToView(Dummy.imperialDummy).forecastWeather)
    }
}