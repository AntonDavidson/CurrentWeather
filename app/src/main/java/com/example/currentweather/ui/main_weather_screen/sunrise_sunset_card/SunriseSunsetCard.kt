package com.example.currentweather.ui.main_weather_screen.sunrise_sunset_card

import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.R
import com.example.currentweather.domain.dummy.Dummy
import com.example.currentweather.ui.components.common.ContentCard
import com.example.currentweather.ui.theme.CurrentWeatherTheme
import com.example.currentweather.ui.viewmodel.mappers.ViewWeatherMapper
import com.example.currentweather.util.currentMillisToHours
import com.example.currentweather.util.extractHourFromTimeString
import com.example.currentweather.util.getCurrentTimeString
import com.example.currentweather.util.getHoursAndMinutesDiff


@Composable
fun SunriseSunsetCardContent(
    sunriseHour: String,
    sunsetHour: String,
    modifier: Modifier = Modifier
) {

    val sunriseHourInt = extractHourFromTimeString(sunriseHour)
    val sunsetHourInt = extractHourFromTimeString(sunsetHour)
    
    ContentCard(modifier) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.padding(bottom = 20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.sunny_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = stringResource(id = R.string.sunrise_sunset),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Box {
                SunriseSunsetVisualizer(
                    sunriseHour = sunriseHourInt,
                    sunsetHour = sunsetHourInt,
                    currentHour = currentMillisToHours(System.currentTimeMillis()),
                    modifier = Modifier.height(140.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Sunrise",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = sunriseHour,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Sunset",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = sunsetHour,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            val (dayLengthHours, dayLengthMinutes) = getHoursAndMinutesDiff(sunriseHour, sunsetHour)
            if (dayLengthHours > 0 || dayLengthMinutes > 0) {
                val apiDayLengthString = if (dayLengthHours > 0) {
                    "Day Length Hours ${dayLengthHours}h ${dayLengthMinutes}m"

                } else {
                    "Day Length Minutes ${dayLengthMinutes}m"
                }
                Text(
                    text = apiDayLengthString,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            val (remainingHours, remainingMinutes) = getHoursAndMinutesDiff(
                getCurrentTimeString(),
                sunsetHour
            )
            if (remainingHours > 0 || remainingMinutes > 0) {
                val remainingDaylightString = if (remainingHours > 0) {
                    "Remaining Light Hours ${remainingHours}h ${remainingMinutes}m"

                } else {
                    "Remaining Light Minutes ${remainingMinutes}m"
                }
                Text(
                    text = remainingDaylightString,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

            }
        }
    }
}

@Composable
private fun SunriseSunsetVisualizer(
    sunriseHour: Int,
    sunsetHour: Int,
    currentHour: Int,
    modifier: Modifier = Modifier
) {
    val colorPrimary = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
    val tertiaryPrimary = MaterialTheme.colorScheme.tertiary


    Canvas(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val dayStart = ((sunriseHour / 24f))
        val dayEnd = ((sunsetHour / 24f))
        val scaleX = canvasWidth / 24f
        val scaleY = canvasHeight / 2f
        var interval = (dayEnd - dayStart) / 2f
        var interval2 = (1f - dayEnd + dayStart) / 2f
        var start = dayStart - (1f - dayEnd + dayStart)
        interval *= 24f * scaleX
        interval2 *= 24f * scaleX
        start *= 24f * scaleX


        drawLine(
            color = colorPrimary,
            start = Offset(0f, canvasHeight / 2),
            end = Offset(canvasWidth, canvasHeight / 2),
            strokeWidth = 1f
        )


        drawIntoCanvas {
            val canvas = it.nativeCanvas
            val sunrisePaint = Paint().apply {
                isAntiAlias = true
                style = this.style.apply { Fill }
                color = colorPrimary.toArgb()
            }
            val sunsetPaint = Paint().apply {
                isAntiAlias = true
                style = this.style.apply { Fill }
                color = tertiaryPrimary.toArgb()
            }
            val futurePaint = Paint().apply {
                isAntiAlias = true
                style = this.style.apply { Fill }
                color = secondaryColor.toArgb()
            }
            val path = Path().apply {
                moveTo(start, scaleY)
                rQuadTo(interval2, scaleY * ((interval2 / interval + 1f) / 2f), interval2 * 2f, 0f)
                rQuadTo(interval, -scaleY * ((interval / interval2 + 1f) / 2f), interval * 2f, 0f)
                rQuadTo(interval2, scaleY * ((interval2 / interval + 1f) / 2f), interval2 * 2f, 0f)
                rQuadTo(interval, -scaleY * ((interval / interval2 + 1f) / 2f), interval * 2f, 0f)
            }

            sun(currentHour, sunriseHour, sunsetHour, scaleY, scaleX)
            canvas.clipPath(path)
            canvas.drawRect(0f, 0f, scaleX * currentHour, scaleY, sunrisePaint)
            canvas.drawRect(0f, scaleY, scaleX * currentHour, canvasHeight, sunsetPaint)
            canvas.drawRect(scaleX * currentHour, 0f, canvasWidth, canvasHeight, futurePaint)
        }
        sun(currentHour, sunriseHour, sunsetHour, scaleY, scaleX)


    }
}


private fun DrawScope.sun(
    currentHour: Int,
    sunriseHour: Int,
    sunsetHour: Int,
    scaleY: Float,
    scaleX: Float
) {
    if (currentHour in sunriseHour..sunsetHour) {
        val totalDaylightHours = sunsetHour - sunriseHour
        val currentDaylightHour = currentHour - sunriseHour

        // Calculate the Y-coordinate for the sun to follow a parabolic path
        val t = currentDaylightHour / totalDaylightHours.toFloat()
        val sunY = 40 + scaleY * (1 - (4 * t * (1 - t))) // Adjust for padding


        val sunSize = 32.dp.toPx() // Adjust the size of the sun
        drawCircle(
            color = Color.Red.copy(alpha = 0.3f),
            radius = sunSize,
            center = Offset(
                x = scaleX * currentHour,
                y = sunY
            )
        )

        drawCircle(
            color = Color.Yellow,
            radius = sunSize / 2,
            center = Offset(
                x = scaleX * currentHour,
                y = sunY
            )
        )
        drawCircle(
            color = Color.Red.copy(alpha = 0.3f),
            radius = sunSize / 4,
            center = Offset(
                x = scaleX * currentHour,
                y = sunY
            )
        )
    }
}


@Preview
@Composable
fun SunriseSunsetCardPreview() {
    val astro =
        ViewWeatherMapper().mapToView(Dummy.imperialDummy).forecastWeather.forecastDays[0].astro
    CurrentWeatherTheme {
        Column {

            SunriseSunsetCardContent(
                sunriseHour = astro.sunrise,
                sunsetHour = astro.sunset,

                )
        }
        Spacer(modifier = Modifier.height(8.dp))

        SunriseSunsetCardContent(
            sunriseHour = astro.sunrise,
            sunsetHour = astro.sunset,

            )
    }
    Spacer(modifier = Modifier.height(8.dp))

    SunriseSunsetCardContent(
        sunriseHour = astro.sunrise,
        sunsetHour = astro.sunset,

        )
}