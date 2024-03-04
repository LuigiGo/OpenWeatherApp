package com.exam.openweatherapp.features.weather.data.model.response


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_weather")
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true) val _id: Int? = null,
    @SerializedName("main") @Embedded("main_") val main: Main,
    @SerializedName("name") val name: String,
    @SerializedName("sys") @Embedded("sys_") val sys: Sys,
    @SerializedName("weather") val weather: List<Weather>? = null,
)