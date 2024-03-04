package com.exam.openweatherapp.features.weather.domain.usecase

import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository

class WeatherGetUserFromPrefUseCase(
    private val repository: WeatherRepository,
) {

    fun execute(): User = repository.getUserFromPref()
}