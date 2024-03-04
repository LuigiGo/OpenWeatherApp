package com.exam.openweatherapp.features.weather.domain.usecase

import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetSavedWeatherUseCase(
    private val repository: WeatherRepository
) {

    fun execute() : List<WeatherResponse> {
        return repository.getSavedWeather()
    }
}