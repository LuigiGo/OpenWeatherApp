package com.exam.openweatherapp.features.weather.data.model.response


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("main") val main: String,
)