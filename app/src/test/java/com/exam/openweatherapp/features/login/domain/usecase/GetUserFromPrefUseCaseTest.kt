package com.exam.openweatherapp.features.login.domain.usecase

import com.exam.openweatherapp.features.login.domain.repository.LoginRepository
import com.exam.openweatherapp.features.registration.data.model.User
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

internal class GetUserFromPrefUseCaseTest {

    private lateinit var mockLoginRepository: LoginRepository
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockLoginRepository = Mockito.mock(LoginRepository::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun execute() {
        Mockito.`when`(mockLoginRepository.getUserFromPref()).thenReturn(mockUser)
        val getUserFromPrefUseCase = GetUserFromPrefUseCase(mockLoginRepository)
        val result = getUserFromPrefUseCase.execute()

        assertThat(result).isInstanceOf(User::class.java)
        assertThat(mockLoginRepository).isInstanceOf(LoginRepository::class.java)
    }
}