package com.example.currentweather.ui.viewmodel.mappers

import com.example.currentweather.domain.model.weather.Weather
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewAstro
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewCondition
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewCurrent
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewDay
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewForecast
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewForecastDay
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewHour
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewLocation
import com.example.currentweather.ui.viewmodel.weather_view_state.model.ViewWeather
import javax.inject.Inject

class ViewWeatherMapper @Inject constructor() : ViewMapper<Weather, ViewWeather> {
    override fun mapToView(input: Weather): ViewWeather {
        return ViewWeather(
            location = ViewLocation(
                name = input.location.name,
                region=input.location.region,
                localtime = input.location.localtime
            ),
            currentWeather = ViewCurrent(
                temperature = input.current.temperature,
                feelsLike = input.current.feelsLike,
                wind = input.current.wind,
                windDegree = input.current.windDegree,
                pressure = input.current.pressure,
                precipitation = input.current.precipitation,
                humidity = input.current.humidity,
                dewPoint = input.current.dewPoint,
                visibility = input.current.visibility,
                uv=input.current.uv,
                condition = ViewCondition(
                    text = input.current.condition.text,
                    icon = input.current.condition.icon,
                )
            ),
            forecastWeather = ViewForecast(
                input.forecast.forecastDay.map { forecastDay ->
                    ViewForecastDay(
                        date = forecastDay.date,
                        day = ViewDay(
                            maxTemp = forecastDay.day.maxTemp,
                            minTemp = forecastDay.day.minTemp,
                            maxWind = forecastDay.day.maxWind,
                            totalPrecip = forecastDay.day.totalPrecip,
                            condition = ViewCondition(
                                text = forecastDay.day.condition.text,
                                icon = forecastDay.day.condition.icon
                            )
                        ),
                        astro = ViewAstro(
                            sunrise = forecastDay.astro.sunrise,
                            sunset = forecastDay.astro.sunset,
                            isMoonUp = forecastDay.astro.isMoonUp,
                            isSunUp = forecastDay.astro.isSunUp
                        ),
                        hourly = forecastDay.hourly.map { hour ->
                            ViewHour(
                                time = hour.time,
                                temperature = hour.temperature,
                                condition = ViewCondition(
                                    text = hour.condition.text,
                                    icon = hour.condition.icon
                                )
                            )
                        },

                    )
                },
            )
        )
    }
}

