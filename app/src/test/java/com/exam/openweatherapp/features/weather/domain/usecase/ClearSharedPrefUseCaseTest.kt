package com.exam.openweatherapp.features.weather.domain.usecase

import com.exam.openweatherapp.features.weather.domain.repository.WeatherRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class ClearSharedPrefUseCaseTest {

    private lateinit var mockWeatherRepository: WeatherRepository

    @Before
    fun setUp() {
        mockWeatherRepository = Mockito.mock(WeatherRepository::class.java)
    }

    @Test
    fun execute() {
        val clearSharedPrefUseCase = ClearSharedPrefUseCase(mockWeatherRepository)
        clearSharedPrefUseCase.execute()

        assertThat(mockWeatherRepository).isInstanceOf(WeatherRepository::class.java)
        verify(mockWeatherRepository, times(1)).clearSharedPrefData()
    }
}