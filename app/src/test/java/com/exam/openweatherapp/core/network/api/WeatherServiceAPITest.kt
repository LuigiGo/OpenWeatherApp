package com.exam.openweatherapp.core.network.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherServiceAPITest {
    private lateinit var service: WeatherServiceAPI
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder().baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherServiceAPI::class.java)
    }

    private fun enqueueMockResponse(
        filename: String,
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filename)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getCurrentWeather_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("weather_response.json")
            val responseBody = service.getCurrentWeather(0.0, 0.0)
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/data/2.5/weather?lat=0.0&lon=0.0&units=metric")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}