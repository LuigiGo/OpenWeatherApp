package com.exam.openweatherapp.features.weather.data.repository.datasourceimpl

import com.exam.openweatherapp.core.utils.base.BaseRepositoryImpl
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.core.network.api.WeatherServiceAPI
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherRemoteDataSource

class WeatherRemoteDataSourceImpl(
    private val weatherServiceAPI: WeatherServiceAPI,
) : WeatherRemoteDataSource, BaseRepositoryImpl() {
    companion object {
        private const val TAG = "WeatherRemoteDataSource"
    }

    override suspend fun getCurrentWeather(weatherRequest: WeatherRequest): ResponseHandler<WeatherResponse> {
        return apiCall {
            weatherServiceAPI.getCurrentWeather(
                weatherRequest.lat, weatherRequest.lon
            )
        }
    }
}