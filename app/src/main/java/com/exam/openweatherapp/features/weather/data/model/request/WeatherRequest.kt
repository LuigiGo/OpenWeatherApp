package com.exam.openweatherapp.features.weather.data.model.request

data class WeatherRequest(
    var lat: Double? = null,
    var lon: Double? = null,
    var units: String? = null,
)