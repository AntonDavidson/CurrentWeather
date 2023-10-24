package com.example.currentweather.data.di

import com.example.currentweather.data.local.RoomDao
import com.example.currentweather.data.remote.api.WeatherService
import com.example.currentweather.data.remote.model.mappers.imperial.ImperialMapper
import com.example.currentweather.data.remote.model.mappers.metric.MetricMapper
import com.example.currentweather.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepo(
        weatherService: WeatherService,
        weatherDao: RoomDao,
        metricMapper:MetricMapper,
        imperialMapper:ImperialMapper
    ): WeatherRepository = WeatherRepository(weatherService, weatherDao,metricMapper,imperialMapper)

}