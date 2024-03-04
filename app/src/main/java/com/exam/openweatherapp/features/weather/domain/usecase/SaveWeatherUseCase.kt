package com.exam.openweatherapp.features.weather.domain.usecase

import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository

class SaveWeatherUseCase(
    private val repository: WeatherRepository,
) {

    suspend fun execute(weatherResponse: WeatherResponse) {
        repository.saveWeather(weatherResponse)
    }
}