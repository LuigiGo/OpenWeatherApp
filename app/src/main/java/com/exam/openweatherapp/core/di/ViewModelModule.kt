package com.exam.openweatherapp.core.di

import com.exam.openweatherapp.features.login.domain.usecase.GetUserFromPrefUseCase
import com.exam.openweatherapp.features.login.domain.usecase.SaveUserToPrefUseCase
import com.exam.openweatherapp.features.login.ui.viewmodel.LoginViewModel
import com.exam.openweatherapp.features.registration.domain.usecase.GetUsersUseCase
import com.exam.openweatherapp.features.registration.domain.usecase.SaveUserUseCase
import com.exam.openweatherapp.features.registration.ui.viewmodel.RegistrationViewModel
import com.exam.openweatherapp.features.weather.domain.usecase.*
import com.exam.openweatherapp.features.weather.ui.viewmodel.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    fun providesWeatherViewModel(
        getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
        saveWeatherUseCase: SaveWeatherUseCase,
        getSavedWeatherUseCase: GetSavedWeatherUseCase,
        clearSharedPrefUseCase: ClearSharedPrefUseCase,
        weatherGetUserFromPrefUseCase: WeatherGetUserFromPrefUseCase,
    ): WeatherViewModel {
        return WeatherViewModel(
            getCurrentWeatherUseCase,
            saveWeatherUseCase,
            getSavedWeatherUseCase,
            clearSharedPrefUseCase,
            weatherGetUserFromPrefUseCase
        )
    }

    @Provides
    fun providesRegistrationViewModel(
        saveUserUseCase: SaveUserUseCase,
        getUsersUseCase: GetUsersUseCase,
    ): RegistrationViewModel {
        return RegistrationViewModel(saveUserUseCase, getUsersUseCase)
    }

    @Provides
    fun providesLoginViewModel(
        getUsersUseCase: GetUsersUseCase,
        saveUserToPrefUseCase: SaveUserToPrefUseCase,
        getUserFromPrefUseCase: GetUserFromPrefUseCase,
    ): LoginViewModel {
        return LoginViewModel(getUsersUseCase, saveUserToPrefUseCase, getUserFromPrefUseCase)
    }
}