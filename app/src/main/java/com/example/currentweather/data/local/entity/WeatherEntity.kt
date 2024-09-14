package com.example.currentweather.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.currentweather.constants.DB_NAME

@Entity(
    tableName = DB_NAME, indices = [Index(value = ["location"], unique = true)]
)
data class WeatherEntity(
    val location: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)