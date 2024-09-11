package com.example.currentweather.ui.main_weather_screen.uv_index


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.currentweather.R
import com.example.currentweather.ui.components.common.InfoContentCard
import com.example.currentweather.ui.components.common.InfoTextContent
import com.example.currentweather.ui.main_weather_screen.uv_index.uvsettings.uvIndexAlertStringRes
import com.example.currentweather.ui.main_weather_screen.uv_index.uvsettings.uvIndexColors
import com.example.currentweather.ui.main_weather_screen.uv_index.uvsettings.uvIndexStringRes


@Composable
fun UvIndexCardContent(
    uvIndex: Double,
) {
    InfoContentCard(
        iconResId = R.drawable.sunny_24,
        titleResId = R.string.uv_index,
    ) {
        InfoTextContent(
            contentText = uvIndex.toString(),
            subText = stringResource(uvIndex.toInt().uvIndexStringRes()),
            hasSubText = true,
            conditionTextResId = uvIndex.toInt().uvIndexAlertStringRes(),
            content = {
                UvIndexBar(uvIndex)
            }
        )

    }
}




@Composable
private fun UvIndexBar(
    uvIndex: Double,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (uvDot, uvBar) = createRefs()
        val biasFloat: Float by animateFloatAsState(
            targetValue = ((uvIndex / 10f).toFloat()),
            animationSpec = tween(
                durationMillis = 3000,
                easing = FastOutSlowInEasing
            ),
            label = "",
        )
        Box(
            modifier
                .padding(start = 4.dp, end = 4.dp)
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = uvIndexColors
                    )
                )
                .constrainAs(uvBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Icon(
            painter = painterResource(R.drawable.baseline_circle_24),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(16.dp)
                .constrainAs(uvDot) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = biasFloat
                    )
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                        bias = 0.5f
                    )
                }
        )
    }
}

@Preview()
@Composable
fun UvIndexCardPreview() {
    MaterialTheme {
        UvIndexCardContent(
            uvIndex = 5.0,
        )
    }
}