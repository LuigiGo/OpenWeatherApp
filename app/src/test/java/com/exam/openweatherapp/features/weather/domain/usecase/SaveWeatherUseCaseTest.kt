package com.exam.openweatherapp.features.weather.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class SaveWeatherUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockRepository: WeatherRepository
    private lateinit var mockWeatherResponse: WeatherResponse

    @Before
    fun setUp() {
        mockRepository = Mockito.mock(WeatherRepository::class.java)
        mockWeatherResponse = Mockito.mock(WeatherResponse::class.java)
    }

    @Test
    fun execute() = runBlocking {
        val savedWeatherUseCase = SaveWeatherUseCase(mockRepository)
        savedWeatherUseCase.execute(mockWeatherResponse)

        assertThat(mockWeatherResponse).isInstanceOf(WeatherResponse::class.java)
        assertThat(mockRepository).isInstanceOf(WeatherRepository::class.java)
        verify(mockRepository, times(1)).saveWeather(mockWeatherResponse)

    }
}