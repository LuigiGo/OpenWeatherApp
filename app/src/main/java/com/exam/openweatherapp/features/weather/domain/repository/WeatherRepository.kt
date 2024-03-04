package com.exam.openweatherapp.features.weather.domain.repository

import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(weatherRequest: WeatherRequest): ResponseHandler<WeatherResponse>

    suspend fun saveWeather(weatherResponse: WeatherResponse)

    fun getSavedWeather(): List<WeatherResponse>

    fun clearSharedPrefData()

    fun getUserFromPref(): User

}