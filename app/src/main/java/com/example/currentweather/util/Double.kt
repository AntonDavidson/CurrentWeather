package com.example.currentweather.util

import kotlin.math.roundToInt

fun Double.temperatureString(): String {
    return try {
        this.roundToInt().toString() + "C°"
    } catch (e: Exception) {
        "${this}°"
    }
}

fun Double.percentageString(): String {
    return try {
        this.roundToInt().toString() + "%"
    } catch (e: Exception) {
        "${this}%"
    }
}

fun Double.speedString(): String {
    return try {
        this.roundToInt().toString() + "km/h"
    } catch (e: Exception) {
        "${this}km/h"
    }
}
fun Double.visibilityString(): String {
    return try {
        this.roundToInt().toString() + " km"
    } catch (e: Exception) {
        "${this}km"
    }
}