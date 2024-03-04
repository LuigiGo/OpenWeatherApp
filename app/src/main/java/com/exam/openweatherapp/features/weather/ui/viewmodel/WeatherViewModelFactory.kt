package com.exam.openweatherapp.features.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory(
    private val weatherViewModel: WeatherViewModel,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return weatherViewModel as T
    }
}