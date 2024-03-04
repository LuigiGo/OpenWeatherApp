package com.exam.openweatherapp.core.utils.helpers

import androidx.room.TypeConverter
import com.exam.openweatherapp.features.weather.data.model.response.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromWeatherList(value: List<Weather>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<Weather> {
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(value, type)
    }
}