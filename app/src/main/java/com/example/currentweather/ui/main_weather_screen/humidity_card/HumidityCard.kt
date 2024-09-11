package com.example.currentweather.ui.main_weather_screen.humidity_card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.currentweather.R
import com.example.currentweather.ui.components.common.InfoContentCard
import com.example.currentweather.ui.components.common.InfoTextContent
import com.example.currentweather.util.temperatureString

@Composable
fun HumidityCardContent(
    humidity: Int,
    dewPoint: Double,
    modifier: Modifier = Modifier
) {
    InfoContentCard(
        iconResId = R.drawable.humidity,
        titleResId = R.string.humidity,
    ) {
        Column(modifier = modifier) {
            InfoTextContent(
                contentText = humidity.toString(),
                conditionTextResId = R.string.the_dew_point_is_x,
                conditionFormat = dewPoint.temperatureString(),
            )
        }
    }
}



@Preview
@Composable
fun HumidityCardPreview() {
    HumidityCardContent(humidity = 80, dewPoint = 17.0)
}