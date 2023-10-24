package com.example.currentweather.ui.main_weather_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.main_weather_screen.current_weather.CurrentWeatherContent
import com.example.currentweather.ui.main_weather_screen.hourly_forecast.HourlyForecastContent
import com.example.currentweather.ui.main_weather_screen.humidity_card.HumidityCardContent
import com.example.currentweather.ui.main_weather_screen.sunrise_sunset_card.SunriseSunsetCardContent
import com.example.currentweather.ui.main_weather_screen.uv_index.UvIndexCardContent
import com.example.currentweather.ui.main_weather_screen.visibility_card.VisibilityCardContent
import com.example.currentweather.ui.main_weather_screen.weekly_forecast.WeeklyForecast
import com.example.currentweather.ui.main_weather_screen.wind_card.WindCardContent
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState

private const val TODAY = 0

@Composable
fun MainWeatherContent(
    viewState: WeatherViewState,
) {

    val scrollState = rememberLazyListState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        state = scrollState,
    ) {
        item {
            CurrentWeatherContent(
                modifier = Modifier.padding(16.dp),
                weather = viewState.weather
            )
        }

        item {
            HourlyForecastContent(
                modifier = Modifier.padding(16.dp),
                hoursList = viewState.weather.forecastWeather.forecastDays[TODAY].hourly,
            )
        }
        item {
            WeeklyForecast(viewState.weather.forecastWeather, modifier = Modifier.padding(16.dp))

        }
        item {
            Row {
                Box(modifier = Modifier.weight(1f)) {

                    WindCardContent(
                        viewState.weather.currentWeather.windSpeed,
                        viewState.weather.currentWeather.windDegree,
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    HumidityCardContent(
                        viewState.weather.currentWeather.humidity,
                        viewState.weather.currentWeather.dewPoint,
                    )

                }
            }
        }
        item {
            Row {
                Box(modifier = Modifier.weight(1f)) {
                    VisibilityCardContent(
                        viewState.weather.currentWeather.visibility,
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    UvIndexCardContent(
                        viewState.weather.currentWeather.uv
                    )
                }
            }
        }
        item {

            SunriseSunsetCardContent(
                sunriseHour = viewState.weather.forecastWeather.forecastDays[TODAY].astro.sunrise,
                sunsetHour = viewState.weather.forecastWeather.forecastDays[TODAY].astro.sunset,
                modifier = Modifier.padding(16.dp),
            )

            Spacer(Modifier.height(10.dp))
        }
    }
}


@Preview
@Composable
fun MainWeatherPreview() {
    MainWeatherContent(
        viewState = WeatherViewState(weather = ViewWeatherMapper().mapToView(Dummy.imperialDummy))
    )
}



