package com.example.currentweather.ui.main_weather_screen.visibility_card

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.currentweather.R
import com.example.currentweather.ui.components.common.InfoContentCard
import com.example.currentweather.ui.components.common.InfoTextContent
import com.example.currentweather.ui.main_weather_screen.visibility_card.visibilitysettings.visibilityConditionsStringRes
import com.example.currentweather.util.visibilityString

@Composable
fun VisibilityCardContent(visibility: Double) {
    InfoContentCard(iconResId = R.drawable.sunny_24, titleResId = R.string.visibility) {
        InfoTextContent(
            contentText = visibility.visibilityString(),
            conditionTextResId = visibility.visibilityConditionsStringRes()
        )
    }
}


@Preview
@Composable
fun VisibilityCardPreview() {
    MaterialTheme {
        VisibilityCardContent(
            visibility = 80.0,
        )
    }
}
