package com.exam.openweatherapp.features.weather.data.repository.datasourceimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exam.openweatherapp.core.network.api.WeatherServiceAPI
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

internal class WeatherRemoteDataSourceImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockWeatherServiceAPI: WeatherServiceAPI

    private lateinit var mockWeatherRequest: WeatherRequest

    @Mock
    private lateinit var mockResponse: Response<WeatherResponse>


    @Test
    fun getCurrentWeather() = runBlocking {
//        Mockito.`when`(
//            mockWeatherServiceAPI.getCurrentWeather(
//                mockWeatherRequest.lat, mockWeatherRequest.lon
//            )
//        ).thenReturn(mockResponse)
//        mockWeatherServiceAPI = Mockito.mock(WeatherServiceAPI::class.java)
//        mockWeatherRequest = Mockito.mock(WeatherRequest::class.java)
//
//        val weatherRemoteDataSourceImpl = WeatherRemoteDataSourceImpl(mockWeatherServiceAPI)
//        weatherRemoteDataSourceImpl.getCurrentWeather(mockWeatherRequest)
    }
}