package com.exam.openweatherapp.features.weather.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exam.openweatherapp.core.db.SharedPrefManagerI
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherLocalDataSource
import com.exam.openweatherapp.features.weather.data.repository.datasource.WeatherRemoteDataSource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class WeatherRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockWeatherRemoteDataSource: WeatherRemoteDataSource
    private lateinit var mockWeatherLocalDataSource: WeatherLocalDataSource
    private lateinit var mockSharedPrefManagerI: SharedPrefManagerI
    private lateinit var mockWeatherResponse: WeatherResponse
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockWeatherRemoteDataSource = Mockito.mock(WeatherRemoteDataSource::class.java)
        mockWeatherLocalDataSource = Mockito.mock(WeatherLocalDataSource::class.java)
        mockSharedPrefManagerI = Mockito.mock(SharedPrefManagerI::class.java)
        mockWeatherResponse = Mockito.mock(WeatherResponse::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun saveWeather() = runBlocking {
        val weatherRepositoryImpl = WeatherRepositoryImpl(
            mockWeatherRemoteDataSource, mockWeatherLocalDataSource, mockSharedPrefManagerI
        )
        weatherRepositoryImpl.saveWeather(mockWeatherResponse)

        assertThat(mockWeatherResponse).isInstanceOf(WeatherResponse::class.java)
        verify(mockWeatherLocalDataSource, times(1)).saveWeather(mockWeatherResponse)
    }

    @Test
    fun getSavedWeather() {
        val weathers = listOf(
            mockWeatherResponse,
            mockWeatherResponse,
            mockWeatherResponse,
        )
        Mockito.`when`(mockWeatherLocalDataSource.getSavedWeather()).thenReturn(weathers)
        val weatherRepositoryImpl = WeatherRepositoryImpl(
            mockWeatherRemoteDataSource, mockWeatherLocalDataSource, mockSharedPrefManagerI
        )
        val result = weatherRepositoryImpl.getSavedWeather()

        with(result) {
            assertThat(this).isNotNull()
            assertThat(this).isInstanceOf(List::class.java)
        }
        verify(mockWeatherLocalDataSource, times(1)).getSavedWeather()
    }

    @Test
    fun clearSharedPrefData() {
        val weatherRepositoryImpl = WeatherRepositoryImpl(
            mockWeatherRemoteDataSource, mockWeatherLocalDataSource, mockSharedPrefManagerI
        )
        weatherRepositoryImpl.clearSharedPrefData()
        verify(mockSharedPrefManagerI, times(1)).clear()
    }

    @Test
    fun getUserFromPref() {
        Mockito.`when`(mockSharedPrefManagerI.getUser()).thenReturn(mockUser)
        val weatherRepositoryImpl = WeatherRepositoryImpl(
            mockWeatherRemoteDataSource, mockWeatherLocalDataSource, mockSharedPrefManagerI
        )
        val result = weatherRepositoryImpl.getUserFromPref()
        assertThat(result).isInstanceOf(User::class.java)
        verify(mockSharedPrefManagerI, times(1)).getUser()

    }
}