package com.example.currentweather.data

interface DataMapper<I, O> {
    fun mapToDomain(input: I): O
}