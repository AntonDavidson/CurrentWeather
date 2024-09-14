package com.example.currentweather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currentweather.constants.DB_NAME
import com.example.currentweather.data.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherLocation(weatherLocation: WeatherEntity)

    @Query("DELETE FROM $DB_NAME WHERE location = :location")
    suspend fun deleteWeatherLocation(location: String)

    @Query("DELETE FROM $DB_NAME")
    suspend fun deleteAll()

    @Query("SELECT * FROM $DB_NAME ")
    fun getAllWeatherLocations(): Flow<List<WeatherEntity>>


}