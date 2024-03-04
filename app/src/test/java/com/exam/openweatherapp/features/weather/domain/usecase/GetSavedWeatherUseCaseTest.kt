package com.exam.openweatherapp.features.weather.domain.usecase

import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class GetSavedWeatherUseCaseTest {

    private lateinit var mockRepository: WeatherRepository
    private lateinit var mockResponse: WeatherResponse

    @Before
    fun setUp() {
        mockRepository = Mockito.mock(WeatherRepository::class.java)
        mockResponse = Mockito.mock(WeatherResponse::class.java)
    }

    @Test
    fun execute() {
        val weathers = listOf(mockResponse, mockResponse)
        Mockito.`when`(mockRepository.getSavedWeather()).thenReturn(weathers)
        val getSavedWeatherUseCase = GetSavedWeatherUseCase(mockRepository)
        val result = getSavedWeatherUseCase.execute()

        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(List::class.java)
        assertThat(mockRepository).isInstanceOf(WeatherRepository::class.java)
        verify(mockRepository, times(1)).getSavedWeather()
    }
}