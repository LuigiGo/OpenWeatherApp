package com.exam.openweatherapp.features.weather.data.repository.datasource

import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse

interface WeatherRemoteDataSource {

    suspend fun getCurrentWeather(weatherRequest: WeatherRequest): ResponseHandler<WeatherResponse>
}