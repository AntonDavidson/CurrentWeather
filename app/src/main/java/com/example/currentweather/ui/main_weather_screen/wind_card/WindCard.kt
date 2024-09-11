package com.example.currentweather.ui.main_weather_screen.wind_card

import android.graphics.Paint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.R
import com.example.currentweather.ui.arrow
import com.example.currentweather.ui.components.common.InfoContentCard
import com.example.currentweather.ui.image_manager.LoadImageIcon
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WindCardContent(
    windSpeed: String,
    windDirection: Int,
) {
    InfoContentCard(iconResId = R.drawable.sunny_24, titleResId = R.string.wind) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            WindDirectionsVisualizer(windDirection = windDirection, windSpeed = windSpeed)
        }
    }
}


@Composable
fun WindDirectionsVisualizer(
    windDirection: Int,
    windSpeed: String,
    modifier: Modifier = Modifier
) {
    var animate by remember { mutableStateOf(false) }
    val arrowAngle: Float by animateFloatAsState(
        targetValue = windDirection.toFloat() * -1,
        animationSpec = tween(
            durationMillis = if (animate) 1000 else 0,
            easing = FastOutSlowInEasing
        ), label = ""
    )
    LaunchedEffect(windDirection) {
        animate = true
    }

    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        DrawMarkers()
        LoadImageIcon(
            modifier = Modifier
                .size(150.dp)
                .rotate(arrowAngle),
            fileName = arrow.asset, tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        WindDirections()
        WindSpeedCircle(windSpeed = windSpeed)
    }
}

@Composable
private fun DrawMarkers() {
    val markersCount = 90
    for (i in 0..markersCount) {
        val angle = i * (360 / markersCount)
        Marker(
            angle = angle,
            drawMarker = i in listOf(0, 11, 22, 34, 45, 57, 68, 80),
        )
    }
}

@Composable
private fun WindDirections() {
    val ternaryColor = MaterialTheme.colorScheme.tertiary
    val northString = stringResource(R.string.north_char)
    val southString = stringResource(R.string.south_char)
    val eastString = stringResource(R.string.east_char)
    val westString = stringResource(R.string.west_char)

    Canvas(Modifier.size(250.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 30f
            color = ternaryColor.toArgb()
        }
        drawIntoCanvas { canvas ->
            canvas.nativeCanvas.apply {
                drawText(northString, center.x, 80f, paint)
                drawText(southString, center.x, canvasHeight - 60f, paint)
                drawText(westString, 80f, center.y, paint)
                drawText(eastString, canvasWidth - 80f, center.y, paint)
            }
        }
    }
}

@Composable
private fun WindSpeedCircle(windSpeed: String, modifier: Modifier = Modifier) {
    val onPrimaryColor = MaterialTheme.colorScheme.primary
    val primaryContainer = MaterialTheme.colorScheme.onPrimary
    val textPaint = Paint().apply {
        color = primaryContainer.toArgb()
        textAlign = android.graphics.Paint.Align.CENTER
        textSize = 24f
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(45.dp) // Adjust size as needed
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val radius = size.minDimension / 2
            drawCircle(
                color = onPrimaryColor,
                radius = radius,
                center = center
            )

            drawIntoCanvas { canvas ->
                val textHeight = textPaint.descent() - textPaint.ascent()
                val textOffset = (textHeight / 2) - textPaint.descent()
                canvas.nativeCanvas.drawText(
                    windSpeed,
                    center.x,
                    center.y + textOffset,
                    textPaint
                )
            }
        }
    }
}

@Composable
private fun Marker(
    angle: Int,
    drawMarker: Boolean,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val ternaryColor = MaterialTheme.colorScheme.tertiary
    Box(
        modifier
            .size(220.dp)
            .drawBehind {
                val drawMarkerSize = if (drawMarker) 20f else 0f
                val theta = (angle - 90) * PI.toFloat() / 180f
                val startRadius = (size.width / 2 * .72f) + drawMarkerSize
                val endRadius = (size.width / 2 * .8f) - drawMarkerSize
                val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
                val endPos = Offset(cos(theta) * endRadius, sin(theta) * endRadius)
                drawLine(
                    color = if (drawMarker) ternaryColor else primaryColor,
                    start = center + startPos,
                    end = center + endPos,
                    strokeWidth = if (drawMarker) 7f else 4f,
                    cap = StrokeCap.Round
                )
            }
    )
}

@Preview
@Composable
fun WindCardPreview() {
    MaterialTheme {
        WindCardContent(
            windSpeed = "10km/h",
            windDirection = 250
        )
    }
}