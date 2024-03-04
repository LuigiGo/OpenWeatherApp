package com.exam.openweatherapp.features.weather.domain.usecase

import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository

class ClearSharedPrefUseCase(private val repository: WeatherRepository) {

    fun execute() {
        repository.clearSharedPrefData()
    }
}