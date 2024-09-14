package com.example.currentweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    navigateToSavedLocations: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = Color.Transparent,
            )
            .fillMaxWidth()
    ) {

        FloatingActionButton(modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(10.dp),
            shape = CircleShape,
            onClick = {
                navigateToSavedLocations()
            }) {
            Icon(Icons.Default.MoreVert, "Saved Locations Screen")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar {

    }
}
