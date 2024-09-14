package com.example.currentweather.data.remote.model.mappers.metric

import com.example.currentweather.data.DataMapper
import com.example.currentweather.data.remote.model.ApiWeatherResponse
import com.example.currentweather.domain.model.weather.Astro
import com.example.currentweather.domain.model.weather.Condition
import com.example.currentweather.domain.model.weather.Current
import com.example.currentweather.domain.model.weather.Day
import com.example.currentweather.domain.model.weather.Forecast
import com.example.currentweather.domain.model.weather.ForecastDay
import com.example.currentweather.domain.model.weather.Hour
import com.example.currentweather.domain.model.weather.Location
import com.example.currentweather.domain.model.weather.Weather
import javax.inject.Inject

class MetricMapper @Inject constructor() : DataMapper<ApiWeatherResponse, Weather> {
    override fun map(input: ApiWeatherResponse): Weather {
        return Weather(
            location = Location(
                name = input.apiLocation?.name ?: "",
                region = input.apiLocation?.region ?: "",
                country = input.apiLocation?.country ?: "",
                lat = input.apiLocation?.lat ?: 0.0,
                lon = input.apiLocation?.lon ?: 0.0,
                timezone = input.apiLocation?.tzId ?: "",
                localtime = input.apiLocation?.localtime ?: ""
            ),
            current = Current(
                temperature = input.apiCurrent?.tempC ?: 0.0,
                feelsLike = input.apiCurrent?.feelslikeC ?: 0.0,
                wind = input.apiCurrent?.windKph ?: 0.0,
                windDegree = input.apiCurrent?.windDegree ?: 0,
                pressure = input.apiCurrent?.pressureMb ?: 0.0,
                precipitation = input.apiCurrent?.precipMm ?: 0.0,
                humidity = input.apiCurrent?.humidity ?: 0,
                cloud = input.apiCurrent?.cloud ?: 0,
                dewPoint = input.apiCurrent?.dewpointC ?: 0.0,
                heatIndex = input.apiCurrent?.heatindexC ?: 0.0,
                visibility = input.apiCurrent?.visKm ?: 0.0,
                gust = input.apiCurrent?.gustKph ?: 0.0,
                uv = input.apiCurrent?.uv ?: 0.0,
                condition = input.apiCurrent?.condition?.let {
                    Condition(
                        text = it.text ?: "",
                        icon = it.icon ?: "",
                        code = it.code ?: 0
                    )
                } ?: Condition("", "", 0)
            ),
            forecast = Forecast(input.apiForecast?.forecastDay?.map { forecastDay ->
                ForecastDay(
                    date = forecastDay.date ?: "",
                    dateEpoch = forecastDay.dateEpoch ?: 0,
                    day = Day(
                        maxTemp = forecastDay.day?.maxtempC ?: 0.0,
                        minTemp = forecastDay.day?.mintempC ?: 0.0,
                        avgTemp = forecastDay.day?.avgtempC ?: 0.0,
                        maxWind = forecastDay.day?.maxwindKph ?: 0.0,
                        totalPrecip = forecastDay.day?.totalprecipMm ?: 0.0,
                        totalSnow = forecastDay.day?.totalsnowCm ?: 0,
                        avgVis = forecastDay.day?.avgvisKm ?: 0.0,
                        avgHumidity = forecastDay.day?.avghumidity ?: 0,
                        dailyWillItRain = forecastDay.day?.dailyWillItRain ?: 0,
                        dailyChanceOfRain = forecastDay.day?.dailyChanceOfRain ?: 0,
                        dailyWillItSnow = forecastDay.day?.dailyWillItSnow ?: 0,
                        dailyChanceOfSnow = forecastDay.day?.dailyChanceOfSnow ?: 0,
                        condition = forecastDay.day?.condition?.let {
                            Condition(
                                text = it.text ?: "",
                                icon = it.icon ?: "",
                                code = it.code ?: 0
                            )
                        } ?: Condition("", "", 0),
                        uv = forecastDay.day?.uv ?: 0.0
                    ),
                    astro = Astro(
                        sunrise = forecastDay.astro?.sunrise ?: "",
                        sunset = forecastDay.astro?.sunset ?: "",
                        moonrise = forecastDay.astro?.moonrise ?: "",
                        moonset = forecastDay.astro?.moonset ?: "",
                        moonPhase = forecastDay.astro?.moonPhase ?: "",
                        moonIllumination = forecastDay.astro?.moonIllumination ?: 0,
                        isMoonUp = forecastDay.astro?.isMoonUp == 1,
                        isSunUp = forecastDay.astro?.isSunUp == 1
                    ),
                    hourly = forecastDay.hour.map { hour ->
                        Hour(
                            time = hour.time ?: "",
                            temperature = hour.tempC ?: 0.0,
                            feelsLike = hour.feelslikeC ?: 0.0,
                            wind = hour.windKph ?: 0.0,
                            pressure = hour.pressureMb ?: 0.0,
                            precipitation = hour.precipMm ?: 0.0,
                            humidity = hour.humidity ?: 0,
                            cloud = hour.cloud ?: 0,
                            dewPoint = hour.dewpointC ?: 0.0,
                            heatIndex = hour.heatindexC ?: 0.0,
                            visibility = hour.visKm ?: 0.0,
                            gust = hour.gustKph ?: 0.0,
                            uv = hour.uv ?: 0.0,
                            condition = hour.condition?.let {
                                Condition(
                                    text = it.text ?: "",
                                    icon = it.icon ?: "",
                                    code = it.code ?: 0
                                )
                            } ?: Condition("", "", 0)
                        )
                    }
                )
            } ?: listOf())
        )
    }
}