package com.exam.openweatherapp.core.di

import android.app.Application
import androidx.room.Room
import com.exam.openweatherapp.core.db.OpenWeatherDatabase
import com.exam.openweatherapp.features.registration.data.db.UserDao
import com.exam.openweatherapp.features.weather.data.db.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesWeatherDatabase(app: Application): OpenWeatherDatabase {
        return Room.databaseBuilder(app, OpenWeatherDatabase::class.java, "db_weather")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesWeatherDao(openWeatherDatabase: OpenWeatherDatabase): WeatherDao {
        return openWeatherDatabase.getWeatherDao()
    }

    @Singleton
    @Provides
    fun providesUserDao(openWeatherDatabase: OpenWeatherDatabase): UserDao {
        return openWeatherDatabase.getUserDao()
    }
}