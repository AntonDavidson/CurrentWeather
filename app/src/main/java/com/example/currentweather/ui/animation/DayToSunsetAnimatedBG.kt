package com.example.currentweather.ui.animation


import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currentweather.R
import kotlinx.coroutines.delay
import java.lang.Float.max
import java.lang.Float.min


@Composable
fun DayToSunsetBackGround(content: @Composable () -> Unit = {}, raining: Boolean, sunset: Boolean) {
    val scope = rememberCoroutineScope()
    val infiniteTransition = rememberInfiniteTransition()
    val isDay = remember {
        mutableStateOf(true)
    }
    val seaMovement by animate(infiniteTransition, 0.01f, 0.2f, 5000)
    val sandMovement by animate(
        infiniteTransition, 0.3f, 0.4f, 10000
    )


    val skyDay by animateColors(
        isDay, Color(0xFF070D2E), Color(0xff4fa6f7)
    )


    val horizonDay by animateColors(
        isDay, Color(0xFFF3BC4F), Color(0xff1c36b8).copy(0.3f)
    )
    val sandColor by animateColors(
        isDay, Color(0xFF977021), Color(0xFFF1C977)
    )


    val skyColors = arrayOf(
        0.1f to if (!raining){skyDay}else{
                                         Color(0xFFC0C0C0)
                                         },
        0.3f to if (isDay.value && !raining) {
            skyDay
        } else if (raining) {
            Color(0xFF8C9399)
        } else {
            Color(0xFF11216F)
        },
        0.9f to horizonDay,
    )

    val earthColors = arrayOf(
        seaMovement to Color(0xff1c36b8),
        sandMovement to sandColor,
        1f to Color(0xfff2eac2),
    )
    val sunColors = arrayOf(
        0.1f to Color(0xFFFFEB3B),
        0.3f to Color(0xFFFFD200),
        0.8f to Color(0xFFFFE717).copy(0.2f),
        1f to Color(0xFFFFE500).copy(0.01f),
    )
    val sunHaloColors = arrayOf(
        0.8f to Color(0xFFFFE717).copy(0.2f),
        1f to Color(0xFFFFE500).copy(0.01f),
    )
    val skyBrush = Brush.verticalGradient(colorStops = skyColors)
    val earthBrush = Brush.verticalGradient(colorStops = earthColors)
    val sunBrush = Brush.radialGradient(colorStops = sunColors)
    val haloBrush = Brush.radialGradient(colorStops = sunHaloColors)

    val sunOffsetX = remember { Animatable(0f) }
    val sunOffsetY = remember { Animatable(0f) }
    val haloRotation by animate(
        infiniteTransition, 180f, 200f, 20000
    )
    val haloOffsetY = remember { Animatable(80f) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .background(brush = skyBrush)
                    .weight(0.3f)
                    .fillMaxSize()
            ) {
                if (raining) {
                    RainAnimation()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {//Top background animation

                    if (!isDay.value) {
                        StarsAnimation()
                    }
                }
                if (isDay.value) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {//Central background animation
                        if (!raining) {
                            BirdsAnimation()
                        }

                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .offset(x = (25).dp, y = 13.5.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.palms),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
                if (!raining) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .offset(x = sunOffsetX.value.dp, y = sunOffsetY.value.dp)
                    ) {//sun

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(sunBrush)
                                .size(200.dp)
                                .align(Alignment.TopEnd)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.sun_background),
                                contentDescription = null,
                                alpha = 0.8f,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .offset(x = sunOffsetX.value.dp, y = sunOffsetY.value.dp)
                    ) {//sun halo
                        Box(
                            modifier = Modifier
                                .size(350.dp)
                                .background(Color.Transparent)
                                .align(Alignment.TopEnd)
                                .rotate(haloRotation)
                                .offset(y = haloOffsetY.value.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(haloBrush)
                                    .size(250.dp)
                                    .padding(15.dp)
                            )
                        }

                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(earthBrush)
                    .weight(0.1f)
            ) {//Earth
                Image(
                    painter = painterResource(id = R.drawable.sand),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.2f
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.islandsand),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 50.dp)
                    )
                }
            }
        }
    }
    if (sunset) {
        LaunchedEffect(Unit) {
            delay(1000)
            animateCelestialBody(
                celestialBodyOffsetX = sunOffsetX,
                targetOffsetX = -180f,
                celestialBodyOffsetY = sunOffsetY,
                targetOffsetY = 440f,
                haloOffset = haloOffsetY,
                targetHaloOffset = 180f,
                scope = scope
            )
            isDay.value = !isDay.value
        }
    }
    content()
}

@Composable
fun RaindropsLayout(modifier: Modifier = Modifier) {
    val raindropCount = 15 // Number of raindrops
    var rotationAngles = emptyList<Float>() // Rotation angles for each raindrop
    for (i in 0..raindropCount) {
        rotationAngles = rotationAngles + ((-20)..(0)).random().toFloat() //Random rotation angles
    }
    Layout(
        modifier = modifier,
        content = {
            (0 until raindropCount).forEach { index ->
                Raindrop(
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(rotationAngles[index])
                )
            }
        }
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = constraints.maxWidth / 100, // raindrop width
                    maxHeight = (constraints.maxHeight).toInt()
                )
            )
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            val xOffset = constraints.maxWidth / (raindropCount + 1)

            placeables.forEachIndexed { index, placeable ->
                placeable.place(
                    x = (index + 1) * xOffset - placeable.width / 2,
                    y = 0
                )
            }
        }
    }
}

@Composable
fun Raindrop(modifier: Modifier) {
    val animateTween by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = (500..1000).random(), easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    Canvas(modifier) {
        val width = size.width.toDp().toPx()
        val x: Float = size.width / 2
        val scopeHeight = size.height - width / 2
        val space = size.height / 2.2f + width / 2
        val spacePos = scopeHeight * animateTween
        val sy1 = spacePos - space / 2
        val sy2 = spacePos + space / 2

        val lineHeight = scopeHeight * 0.0505f // Adjust the raindrop length

        val line1y1 = max(0f, sy1 - lineHeight)
        val line1y2 = max(line1y1, sy1)

        val line2y1 = min(sy2, scopeHeight)
        val line2y2 = min(line2y1 + lineHeight, scopeHeight)

        val raindropColor = Color(0x884286F4) // Watery color

        drawLine(
            raindropColor,
            Offset(x, line1y1),
            Offset(x, line1y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(raindropColor),
            cap = StrokeCap.Round
        )

        drawLine(
            raindropColor,
            Offset(x, line2y1),
            Offset(x, line2y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(raindropColor),
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun RainAnimation() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RaindropsLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}