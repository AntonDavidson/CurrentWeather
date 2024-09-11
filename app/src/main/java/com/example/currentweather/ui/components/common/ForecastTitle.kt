package com.example.currentweather.ui.components.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ForecastTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 10.dp,
                top = 10.dp
            ),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}