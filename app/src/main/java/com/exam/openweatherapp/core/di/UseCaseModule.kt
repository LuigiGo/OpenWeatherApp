package com.exam.openweatherapp.core.di

import com.exam.openweatherapp.features.login.domain.repository.LoginRepository
import com.exam.openweatherapp.features.login.domain.usecase.GetUserFromPrefUseCase
import com.exam.openweatherapp.features.login.domain.usecase.SaveUserToPrefUseCase
import com.exam.openweatherapp.features.registration.domain.repository.RegistrationRepository
import com.exam.openweatherapp.features.registration.domain.usecase.GetUsersUseCase
import com.exam.openweatherapp.features.registration.domain.usecase.SaveUserUseCase
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository
import com.exam.openweatherapp.features.weather.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    /** Weather use cases*/
    @Singleton
    @Provides
    fun providesCurrentWeatherUseCase(weatherRepository: WeatherRepository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    fun providesSaveWeatherUseCase(weatherRepository: WeatherRepository): SaveWeatherUseCase {
        return SaveWeatherUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    fun providesGetSavedWeatherUseCase(weatherRepository: WeatherRepository): GetSavedWeatherUseCase {
        return GetSavedWeatherUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    fun providesClearSharedPrefUseCase(repository: WeatherRepository): ClearSharedPrefUseCase {
        return ClearSharedPrefUseCase(repository)
    }

    /** Registration use cases*/
    @Singleton
    @Provides
    fun providesSaveUserUseCase(registrationRepository: RegistrationRepository): SaveUserUseCase {
        return SaveUserUseCase(registrationRepository)
    }

    @Singleton
    @Provides
    fun providesGetRegisteredUsers(registrationRepository: RegistrationRepository): GetUsersUseCase {
        return GetUsersUseCase(registrationRepository)
    }

    /** Login use cases*/
    @Singleton
    @Provides
    fun providesSaveUserToPrefUseCase(loginRepository: LoginRepository): SaveUserToPrefUseCase {
        return SaveUserToPrefUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun providesGetUserFromSharedPref(loginRepository: LoginRepository): GetUserFromPrefUseCase {
        return GetUserFromPrefUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun providesWeatherGetUserFromSharedPref(weatherRepository: WeatherRepository): WeatherGetUserFromPrefUseCase {
        return WeatherGetUserFromPrefUseCase(
            weatherRepository
        )
    }

}