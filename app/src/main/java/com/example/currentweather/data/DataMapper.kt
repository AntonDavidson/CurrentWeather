package com.example.currentweather.data

interface DataMapper<I, O> {
    fun map(input: I): O
}