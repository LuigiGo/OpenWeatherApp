package com.exam.openweatherapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exam.openweatherapp.core.utils.helpers.DataConverter
import com.exam.openweatherapp.features.registration.data.db.UserDao
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.weather.data.db.WeatherDao
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse

@Database(
    entities = [WeatherResponse::class, User::class], version = 1, exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class OpenWeatherDatabase : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao

    abstract fun getUserDao(): UserDao

}