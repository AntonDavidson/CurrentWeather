package com.example.currentweather.util

fun removeTrailingSpace(input: String): String {
    return input.replace(Regex("\\s+$"), "")
}