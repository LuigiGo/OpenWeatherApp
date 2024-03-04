package com.exam.openweatherapp.features.weather.data.repository.datasource

import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherLocalDataSource {

    suspend fun saveWeather(weatherResponse: WeatherResponse)

    fun getSavedWeather(): List<WeatherResponse>
}