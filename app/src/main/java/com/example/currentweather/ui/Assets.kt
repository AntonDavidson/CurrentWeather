package com.example.currentweather.ui

sealed class Assets(val asset: String)
data object arrow : Assets("arrow.png")