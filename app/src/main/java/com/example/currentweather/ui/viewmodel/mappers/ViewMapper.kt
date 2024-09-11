package com.example.currentweather.ui.viewmodel.mappers

interface ViewMapper <I, O> {
    fun mapToView(input: I): O
}