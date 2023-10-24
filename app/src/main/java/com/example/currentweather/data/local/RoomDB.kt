package com.example.currentweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currentweather.data.local.entity.WeatherEntity


@Database(
    entities = [WeatherEntity::class],
    version = 2,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract val weatherDao: RoomDao
}