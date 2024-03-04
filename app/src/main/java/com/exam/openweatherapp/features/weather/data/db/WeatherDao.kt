package com.exam.openweatherapp.features.weather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeather(weatherResponse: WeatherResponse)

    @Query("SELECT * FROM tbl_weather")
    fun getSavedWeather(): List<WeatherResponse>

}