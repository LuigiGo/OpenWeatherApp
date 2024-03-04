package com.exam.openweatherapp.core.di

import com.exam.openweatherapp.features.login.ui.viewmodel.LoginViewModel
import com.exam.openweatherapp.features.login.ui.viewmodel.LoginViewModelFactory
import com.exam.openweatherapp.features.registration.ui.viewmodel.RegistrationViewModel
import com.exam.openweatherapp.features.registration.ui.viewmodel.RegistrationViewModelFactory
import com.exam.openweatherapp.features.weather.ui.viewmodel.WeatherViewModel
import com.exam.openweatherapp.features.weather.ui.viewmodel.WeatherViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    fun providesWeatherViewModelFactory(weatherViewModel: WeatherViewModel): WeatherViewModelFactory {
        return WeatherViewModelFactory(weatherViewModel)
    }

    @Provides
    fun providesRegistrationViewModelFactory(registrationViewModel: RegistrationViewModel): RegistrationViewModelFactory {
        return RegistrationViewModelFactory(registrationViewModel)
    }

    @Provides
    fun providesLoginViewModelFactory(loginViewModel: LoginViewModel): LoginViewModelFactory {
        return LoginViewModelFactory(loginViewModel)
    }
}