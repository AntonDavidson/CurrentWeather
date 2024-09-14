package com.example.currentweather.ui.saved_locations

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onSearchActionClick: () -> Unit = {},
    onValueChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (!focusState.hasFocus) {
                    keyboardController?.hide()
                }
            },
        shape = CircleShape.copy(CornerSize(30.dp)),
        value = value,
        label = {
            Text(text = "Search", color = MaterialTheme.colorScheme.primary)
        },
        onValueChange = onValueChanged,
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                null,
                tint = MaterialTheme.colorScheme.primary.copy(0.2f)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchActionClick.invoke()
                keyboardController?.hide()
            }
        ), colors = TextFieldDefaults.colors(
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
            focusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.2f),
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(0.1f),
            focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.4f)
        )
    )
}

