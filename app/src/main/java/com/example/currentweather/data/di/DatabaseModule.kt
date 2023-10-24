package com.example.currentweather.data.di

import android.content.Context
import androidx.room.Room
import com.example.currentweather.constants.DB_NAME
import com.example.currentweather.data.local.RoomDB
import com.example.currentweather.data.local.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(
            context,
            RoomDB::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(
        weatherDao: RoomDB
    ): RoomDao {
        return weatherDao.weatherDao
    }


}