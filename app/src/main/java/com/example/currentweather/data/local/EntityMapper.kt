package com.example.currentweather.data.local

import com.example.currentweather.data.DataMapper
import com.example.currentweather.data.local.entity.WeatherEntity

class EntityMapper : DataMapper<String, WeatherEntity> {
    override fun map(input: String): WeatherEntity {
        return WeatherEntity(location = input)
    }
}