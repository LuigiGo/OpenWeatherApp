package com.exam.openweatherapp.features.weather.ui.viewmodel

import androidx.lifecycle.*
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.domain.usecase.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
    private val getSavedWeatherUseCase: GetSavedWeatherUseCase,
    private val clearSharedPrefUseCase: ClearSharedPrefUseCase,
    private val weatherGetUserFromPrefUseCase: WeatherGetUserFromPrefUseCase,
) : ViewModel() {

    private val _currentWeatherResponse: MutableLiveData<ResponseHandler<WeatherResponse>> =
        MutableLiveData()
    val currentWeatherResponse: LiveData<ResponseHandler<WeatherResponse>> = _currentWeatherResponse

    private val _user: MutableLiveData<ResponseHandler<User>> = MutableLiveData()
    val user: LiveData<ResponseHandler<User>> = _user.distinctUntilChanged()

    private val _savedWeathers: MutableLiveData<ResponseHandler<List<WeatherResponse>>> =
        MutableLiveData()
    val savedWeathers: LiveData<ResponseHandler<List<WeatherResponse>>> =
        _savedWeathers.distinctUntilChanged()

    fun getCurrentWeather(weatherRequest: WeatherRequest) = viewModelScope.launch(IO) {
        _currentWeatherResponse.postValue(ResponseHandler.Loading())
        val response = getCurrentWeatherUseCase.execute(weatherRequest)
        response.data?.let { weather ->
            saveWeather(weather)
        }
        _currentWeatherResponse.postValue(response)

    }

    private suspend fun saveWeather(weatherResponse: WeatherResponse) = viewModelScope.launch(IO) {
        saveWeatherUseCase.execute(weatherResponse)
    }

    fun getSavedWeather() = viewModelScope.launch(IO) {
        val weathers = getSavedWeatherUseCase.execute()
        _savedWeathers.postValue(ResponseHandler.Success(weathers))
    }

    fun getUserFromPref() {
        val user = weatherGetUserFromPrefUseCase.execute()
        _user.postValue(ResponseHandler.Success(user))

    }

    fun clearSharedPref() {
        clearSharedPrefUseCase.execute()
    }
}