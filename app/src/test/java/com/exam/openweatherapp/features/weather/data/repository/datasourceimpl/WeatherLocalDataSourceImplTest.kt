package com.exam.openweatherapp.features.weather.data.repository.datasourceimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exam.openweatherapp.features.weather.data.db.WeatherDao
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

internal class WeatherLocalDataSourceImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockWeatherDao: WeatherDao

    private lateinit var mockWeatherResponse: WeatherResponse

    @Before
    fun setUp() {
        mockWeatherDao = Mockito.mock(WeatherDao::class.java)
        mockWeatherResponse = Mockito.mock(WeatherResponse::class.java)
    }

    @Test
    fun saveWeather() = runBlocking {
        val weatherLocalDataSourceImpl = WeatherLocalDataSourceImpl(mockWeatherDao)
        weatherLocalDataSourceImpl.saveWeather(mockWeatherResponse)

        assertThat(mockWeatherDao).isNotNull()
        verify(mockWeatherDao, times(1)).saveWeather(mockWeatherResponse)
    }

    @Test
    fun getSavedWeather() {
        val savedWeathers = listOf(
            mockWeatherResponse,
            mockWeatherResponse,
        )
        Mockito.`when`(mockWeatherDao.getSavedWeather()).thenReturn(savedWeathers)
        val weatherLocalDataSourceImpl = WeatherLocalDataSourceImpl(mockWeatherDao)
        val result = weatherLocalDataSourceImpl.getSavedWeather()

        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(List::class.java)
        assertThat(mockWeatherDao).isNotNull()
    }
}