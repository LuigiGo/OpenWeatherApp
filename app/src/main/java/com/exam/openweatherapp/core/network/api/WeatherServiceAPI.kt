package com.exam.openweatherapp.core.network.api

import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceAPI {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
        @Query("units") unit: String = "metric",
    ): Response<WeatherResponse>
}