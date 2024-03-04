package com.exam.openweatherapp.features.weather.data.repository.datasourceimpl

import com.exam.openweatherapp.features.weather.data.db.WeatherDao
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherLocalDataSource
import kotlinx.coroutines.flow.Flow

class WeatherLocalDataSourceImpl(
    private val weatherDao: WeatherDao,
) : WeatherLocalDataSource {
    override suspend fun saveWeather(weatherResponse: WeatherResponse) {
        weatherDao.saveWeather(weatherResponse)
    }

    override fun getSavedWeather(): List<WeatherResponse> {
        return weatherDao.getSavedWeather()
    }
}