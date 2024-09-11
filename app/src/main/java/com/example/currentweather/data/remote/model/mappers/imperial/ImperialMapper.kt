package com.example.currentweather.data.remote.model.mappers.imperial

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

class ImperialMapper @Inject constructor() : DataMapper<ApiWeatherResponse, Weather> {
    override fun mapToDomain(input: ApiWeatherResponse): Weather {
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
                temperature = input.apiCurrent?.tempF ?: 0.0,
                feelsLike = input.apiCurrent?.feelslikeF ?: 0.0,
                wind = input.apiCurrent?.windMph ?: 0.0,
                windDegree = input.apiCurrent?.windDegree ?: 0,
                pressure = input.apiCurrent?.pressureIn ?: 0.0,
                precipitation = input.apiCurrent?.precipIn ?: 0.0,
                humidity = input.apiCurrent?.humidity ?: 0,
                cloud = input.apiCurrent?.cloud ?: 0,
                dewPoint = input.apiCurrent?.dewpointF ?: 0.0,
                heatIndex = input.apiCurrent?.heatindexF ?: 0.0,
                visibility = input.apiCurrent?.visMiles ?: 0.0,
                gust = input.apiCurrent?.gustMph ?: 0.0,
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
                        maxTemp = forecastDay.day?.maxtempF ?: 0.0,
                        minTemp = forecastDay.day?.mintempF ?: 0.0,
                        avgTemp = forecastDay.day?.avgtempF ?: 0.0,
                        maxWind = forecastDay.day?.maxwindMph ?: 0.0,
                        totalPrecip = forecastDay.day?.totalprecipIn ?: 0.0,
                        totalSnow = convertCmToInches(forecastDay.day?.totalsnowCm ?: 0),
                        avgVis = forecastDay.day?.avgvisMiles ?: 0.0,
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
                        uv = forecastDay.day?.uv ?: 0.0),
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
                            temperature = hour.tempF ?: 0.0,
                            feelsLike = hour.feelslikeF ?: 0.0,
                            wind = hour.windMph ?: 0.0,
                            pressure = hour.pressureIn ?: 0.0,
                            precipitation = hour.precipIn ?: 0.0,
                            humidity = hour.humidity ?: 0,
                            cloud = hour.cloud ?: 0,
                            dewPoint = hour.dewpointF ?: 0.0,
                            heatIndex = hour.heatindexF ?: 0.0,
                            visibility = hour.visMiles ?: 0.0,
                            gust = hour.gustMph ?: 0.0,
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

fun convertCmToInches(cm: Int): Int {
    return (cm * 0.393701).toInt()
}