package com.exam.openweatherapp.features.login.domain.usecase

import com.exam.openweatherapp.features.login.domain.repository.LoginRepository
import com.exam.openweatherapp.features.registration.data.model.User
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

internal class SaveUserToPrefUseCaseTest {

    private lateinit var mockLoginRepository: LoginRepository
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockLoginRepository = Mockito.mock(LoginRepository::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun execute() {
        val saveUserToPrefUseCase = SaveUserToPrefUseCase(mockLoginRepository)
        saveUserToPrefUseCase.execute(mockUser)

        assertThat(mockUser).isInstanceOf(User::class.java)
        assertThat(mockLoginRepository).isInstanceOf(LoginRepository::class.java)
        verify(mockLoginRepository, times(1)).saveUserToPref(mockUser)
    }
}