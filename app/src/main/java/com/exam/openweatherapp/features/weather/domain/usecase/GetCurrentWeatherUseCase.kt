package com.exam.openweatherapp.features.weather.domain.usecase

import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository,
) {
    suspend fun execute(weatherRequest: WeatherRequest): ResponseHandler<WeatherResponse> {
        return repository.getCurrentWeather(weatherRequest)
    }
}