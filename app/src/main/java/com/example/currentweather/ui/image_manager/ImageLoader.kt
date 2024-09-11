package com.example.currentweather.ui.image_manager

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


suspend fun String.loadImageFromAssets(context: Context): ImageBitmap? {
    val assetManager = context.assets
    return withContext(Dispatchers.IO) {
        try {
            val inputStream = assetManager.open(this@loadImageFromAssets)
            val bitmap = BitmapFactory.decodeStream(inputStream).asImageBitmap()
            inputStream.close()
            bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}

@Composable
fun LoadImageIcon(
    modifier: Modifier = Modifier,
    fileName: String,
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(fileName) {
        coroutineScope.launch {
            imageBitmap = fileName.loadImageFromAssets(context)
        }
    }
    imageBitmap?.let {
        Icon(
            painter = BitmapPainter(it),
            contentDescription = null, tint = tint,
            modifier = modifier // Adjust the size as needed
        )
    }
}

@Composable
fun LoadImageFromNetwork(modifier: Modifier = Modifier, imageUrl: String) {
    val context = LocalContext.current
    var bitmapImage by remember { mutableStateOf<ImageBitmap?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect("mockweatherimage.png") {
        coroutineScope.launch {
            bitmapImage = "mockweatherimage.png".loadImageFromAssets(context)
        }
    }

    Box(modifier = modifier) {
        bitmapImage?.let {
            AsyncImage(
                model = "http://$imageUrl",
                contentDescription = "",
                placeholder = BitmapPainter(it),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}