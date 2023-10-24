package com.example.currentweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.ui.theme.BlackBackground

@Composable
fun BottomBar(
    currentTemp: Int,
    locationRequest: () -> Unit,
    onSaveLocation: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                color = BlackBackground.copy(0.1f),
                shape = CircleShape.copy(CornerSize(25.dp))
            )
            .fillMaxSize()
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${currentTemp}C°",
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(10.dp)
        )
        FloatingActionButton(modifier = Modifier.align(Alignment.Center), onClick = {
//            Log.i("TAG", "BottomBarButton: ${currentWeatherData.apiLocation.name} saved")
//            onSaveLocation(currentWeatherData.apiLocation.name)
        }) {

            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
        FloatingActionButton(modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(10.dp),
            shape = CircleShape,
            onClick = {
                locationRequest()
            }) {
            Icon(Icons.Outlined.LocationOn, "ApiLocation Settings")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(currentTemp = 15, locationRequest = { /*TODO*/ }, onSaveLocation = {})
}
