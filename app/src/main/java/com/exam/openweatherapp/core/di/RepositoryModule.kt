package com.exam.openweatherapp.core.di

import com.exam.openweatherapp.core.db.SharedPrefManagerI
import com.exam.openweatherapp.features.login.data.repository.LoginRepositoryImpl
import com.exam.openweatherapp.features.login.data.repository.datasource.LoginLocalDataSource
import com.exam.openweatherapp.features.login.domain.repository.LoginRepository
import com.exam.openweatherapp.features.registration.data.repository.RegistrationRepositoryImpl
import com.exam.openweatherapp.features.registration.data.repository.datasource.RegistrationLocalDataSource
import com.exam.openweatherapp.features.registration.domain.repository.RegistrationRepository
import com.exam.openweatherapp.features.weather.data.repository.WeatherRepositoryImpl
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherLocalDataSource
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherRemoteDataSource
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesWeatherRepositoryImpl(
        weatherRemoteDataSource: WeatherRemoteDataSource,
        weatherLocalDataSource: WeatherLocalDataSource,
        sharedPrefManagerI: SharedPrefManagerI,
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            weatherRemoteDataSource,
            weatherLocalDataSource,
            sharedPrefManagerI
        )
    }

    @Singleton
    @Provides
    fun providesRegistrationRepositoryImpl(localDataSource: RegistrationLocalDataSource): RegistrationRepository {
        return RegistrationRepositoryImpl(localDataSource)
    }

    @Singleton
    @Provides
    fun providesLoginRepositoryImpl(
        loginLocalDataSource: LoginLocalDataSource,
        sharedPrefManagerI: SharedPrefManagerI,
    ): LoginRepository {
        return LoginRepositoryImpl(loginLocalDataSource, sharedPrefManagerI)
    }
}