package com.example.currentweather.domain.dummy

import com.example.currentweather.domain.model.weather.Astro
import com.example.currentweather.domain.model.weather.Condition
import com.example.currentweather.domain.model.weather.Current
import com.example.currentweather.domain.model.weather.Day
import com.example.currentweather.domain.model.weather.Forecast
import com.example.currentweather.domain.model.weather.ForecastDay
import com.example.currentweather.domain.model.weather.Hour
import com.example.currentweather.domain.model.weather.Location
import com.example.currentweather.domain.model.weather.Weather

object Dummy {
    val metricDummy = Weather(
        location = Location(
            name = "London",
            region = "England",
            country = "UK",
            lat = 51.5074,
            lon = -0.1278,
            timezone = "Europe/London",
            localtime = "2024-08-23 14:00"
        ), current = Current(
            temperature = 20.3,
            feelsLike = 21.0,
            wind = 15.6,
            windDegree = 29,
            pressure = 1012.0,
            precipitation = 0.2,
            humidity = 80,
            cloud = 50,
            dewPoint = 16.0,
            heatIndex = 21.5,
            visibility = 10.0,
            gust = 18.0,
            uv = 4.9,
            condition = Condition(
                text = "Overcast",
                icon = "//cdn.weatherapi.com/weather/64x64/day/122.png",
                code = 1009
            )
        ), forecast =
        Forecast(
            listOf(
                ForecastDay(
                    date = "2024-08-23",
                    dateEpoch = 22, day =
                    Day(
                        maxTemp = 22.0,
                        minTemp = 15.0,
                        avgTemp = 18.5,
                        maxWind = 20.0,
                        totalPrecip = 2.0,
                        avgVis = 10.0,
                        avgHumidity = 75,
                        dailyChanceOfRain = 2,
                        dailyChanceOfSnow = 2,
                        dailyWillItSnow = 1,
                        dailyWillItRain = 0,
                        totalSnow = 2,
                        condition = Condition(
                            text = "Rainy",
                            icon = "//cdn.weatherapi.com/weather/64x64/day/296.png",
                            code = 1183
                        ),
                        uv = 5.0
                    ),
                    astro = Astro(
                        sunrise = "05:30 AM",
                        sunset = "08:15 PM",
                        moonrise = "10:30 PM",
                        moonset = "08:00 AM",
                        moonPhase = "Waning Crescent",
                        moonIllumination = 30,
                        isMoonUp = true,
                        isSunUp = true
                    ),
                    hourly = listOf(
                        Hour(
                            time = "2024-08-23 14:00",
                            temperature = 20.0,
                            feelsLike = 21.0,
                            wind = 15.6,
                            pressure = 1012.0,
                            precipitation = 0.2,
                            humidity = 80,
                            cloud = 50,
                            dewPoint = 16.0,
                            heatIndex = 21.0,
                            visibility = 10.0,
                            gust = 18.0,
                            uv = 4.0,
                            condition = Condition(
                                text = "Overcast",
                                icon = "//cdn.weatherapi.com/weather/64x64/day/122.png",
                                code = 1009
                            )
                        )
                    )
                )
            )
        )
    )


    val imperialDummy = Weather(
        location = Location(
            name = "New York",
            region = "New York",
            country = "USA",
            lat = 40.7128,
            lon = -74.0060,
            timezone = "America/New_York",
            localtime = "2024-08-23 14:00"
        ), current = Current(
            temperature = 85.3,
            feelsLike = 88.0,
            wind = 5.6,
            windDegree = 87,
            pressure = 30.12,
            precipitation = 0.1,
            humidity = 70,
            cloud = 40,
            dewPoint = 70.0,
            heatIndex = 89.5,
            visibility = 10.0,
            gust = 7.8,
            uv = 9.0,
            condition = Condition(
                text = "Partly Cloudy",
                icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                code = 1003
            )
        ), forecast = Forecast(
            forecastDay =
            listOf(
                ForecastDay(
                    date = "2024-08-23", dateEpoch = 2222,
                    day = Day(
                        maxTemp = 90.0,
                        minTemp = 72.0,
                        avgTemp = 81.0,
                        maxWind = 10.0,
                        totalPrecip = 0.2,
                        avgVis = 10.0,
                        avgHumidity = 65,
                        dailyWillItSnow = 1,
                        dailyWillItRain = 1,
                        dailyChanceOfSnow = 45,
                        dailyChanceOfRain = 22,
                        totalSnow = 1,
                        condition = Condition(
                            text = "Sunny",
                            icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                            code = 1000
                        ),
                        uv = 7.0,
                    ), astro = Astro(
                        sunrise = "06:00 AM",
                        sunset = "07:45 PM",
                        moonrise = "11:00 PM",
                        moonset = "09:00 AM",
                        moonPhase = "Waning Gibbous",
                        moonIllumination = 50,
                        isMoonUp = true,
                        isSunUp = true
                    ), hourly = listOf(
                        Hour(
                            time = "2024-08-23 14:00",
                            temperature = 85.0,
                            feelsLike = 88.0,
                            wind = 5.6,
                            pressure = 30.12,
                            precipitation = 0.1,
                            humidity = 70,
                            cloud = 40,
                            dewPoint = 70.0,
                            heatIndex = 89.0,
                            visibility = 10.0,
                            gust = 7.8,
                            uv = 6.0,
                            condition = Condition(
                                text = "Partly Cloudy",
                                icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                                code = 1003
                            )
                        )
                    )
                )
            )
        )
    )
}









