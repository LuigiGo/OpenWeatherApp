package com.exam.openweatherapp.core.di

import com.exam.openweatherapp.core.network.api.WeatherServiceAPI
import com.exam.openweatherapp.features.login.data.repository.datasource.LoginLocalDataSource
import com.exam.openweatherapp.features.login.data.repository.datasourceImpl.LoginLocalDataSourceImpl
import com.exam.openweatherapp.features.registration.data.db.UserDao
import com.exam.openweatherapp.features.registration.data.repository.datasource.RegistrationLocalDataSource
import com.exam.openweatherapp.features.registration.data.repository.datasourceImpl.RegistrationLocalDataSourceImpl
import com.exam.openweatherapp.features.weather.data.db.WeatherDao
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherLocalDataSource
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherRemoteDataSource
import com.exam.openweatherapp.features.weather.data.repository.datasourceimpl.WeatherLocalDataSourceImpl
import com.exam.openweatherapp.features.weather.data.repository.datasourceimpl.WeatherRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesWeatherRemoteDataSource(weatherServiceAPI: WeatherServiceAPI): WeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl(weatherServiceAPI)
    }

    @Singleton
    @Provides
    fun providesWeatherLocalDataSource(weatherDao: WeatherDao): WeatherLocalDataSource {
        return WeatherLocalDataSourceImpl(weatherDao)
    }

    @Singleton
    @Provides
    fun providesRegistrationLocalDataSource(userDao: UserDao): RegistrationLocalDataSource {
        return RegistrationLocalDataSourceImpl(userDao)
    }

    @Singleton
    @Provides
    fun providesLoginLocalDataSource(
        userDao: UserDao,
    ): LoginLocalDataSource {
        return LoginLocalDataSourceImpl(userDao)
    }
}