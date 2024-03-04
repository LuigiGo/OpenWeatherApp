package com.exam.openweatherapp.features.weather.data.model.response


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") val temp: Double,
)