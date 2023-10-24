package com.example.currentweather.data.local

import androidx.room.*
import com.example.currentweather.constants.DB_NAME
import com.example.currentweather.data.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWeatherLocation(weatherLocation: WeatherEntity)

    @Delete
    fun deleteWeatherLocation(weatherLocation: WeatherEntity)

    @Query("DELETE FROM $DB_NAME")
    suspend fun deleteAll()

    @Query("SELECT * FROM $DB_NAME ")
    fun getAllWeatherLocations(): Flow<List<WeatherEntity>>

    @Query("SELECT * FROM $DB_NAME WHERE id = :id")
    suspend fun getWeatherLocation(id: Long): WeatherEntity?
}