package com.example.currentweather.ui.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.currentweather.ui.saved_locations.SearchBar

@Composable
 fun ErrorContent(onSearch: (params: String) -> Unit) {
    val textValue = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Something went wrong\n" + "\n Try to search again...".trim(),
            color = Color.White,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(50.dp),
        )
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp),
            value = textValue.value,
            onValueChanged = {
                textValue.value = it
            },
            onSearchActionClick = {
                onSearch(textValue.value)
            },
        )
        CircularProgressIndicator(
            Modifier
                .size(80.dp)
                .align(Alignment.Center)
        )

    }
}
