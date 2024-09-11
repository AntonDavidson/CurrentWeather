package com.example.currentweather.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun InfoContentCard(
    iconResId: Int,
    titleResId: Int,
    content: @Composable () -> Unit
) {
    ContentCard(
        modifier = Modifier
            .padding(12.dp)
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Box {
                MediumIconTitle(
                    icon = iconResId,
                    title = stringResource(id = titleResId)
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            content()
        }
    }
}

@Composable
fun InfoTextContent(
    contentText: String,
    subText: String = "",
    hasSubText: Boolean = false,
    content: @Composable () -> Unit = {},
    conditionTextResId: Int,
    conditionFormat: String = "",

    ) {
    Text(
        text = contentText,
        style = MaterialTheme.typography.displaySmall.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
    if (hasSubText) {
        Text(
            text = subText,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
    content()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = conditionTextResId, conditionFormat),
            style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}