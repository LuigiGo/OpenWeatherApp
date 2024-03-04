package com.exam.openweatherapp.features.weather.data.repository

import com.exam.openweatherapp.core.db.SharedPrefManagerI
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherLocalDataSource
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherRemoteDataSource
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val sharedPrefManager: SharedPrefManagerI,
) : WeatherRepository {

    override suspend fun getCurrentWeather(weatherRequest: WeatherRequest): ResponseHandler<WeatherResponse> {
        return weatherRemoteDataSource.getCurrentWeather(weatherRequest)
    }

    override suspend fun saveWeather(weatherResponse: WeatherResponse) {
        weatherLocalDataSource.saveWeather(weatherResponse)
    }

    override fun getSavedWeather(): List<WeatherResponse> {
        return weatherLocalDataSource.getSavedWeather()
    }

    override fun clearSharedPrefData() {
        sharedPrefManager.clear()
    }

    override fun getUserFromPref(): User {
        return sharedPrefManager.getUser()
    }
}