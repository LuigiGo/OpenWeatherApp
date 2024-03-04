package com.exam.openweatherapp.features.registration.domain.usecase

import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.data.repository.RegistrationRepositoryImpl
import com.exam.openweatherapp.features.registration.domain.repository.RegistrationRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

internal class SaveUserUseCaseTest {

    private lateinit var mockRepository: RegistrationRepository
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockRepository = Mockito.mock(RegistrationRepositoryImpl::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun execute() = runBlocking {
        val saveUserUseCase = SaveUserUseCase(mockRepository)
        saveUserUseCase.execute(mockUser)

        assertThat(mockUser).isInstanceOf(User::class.java)
        assertThat(mockRepository).isInstanceOf(RegistrationRepository::class.java)
        verify(mockRepository, times(1)).saveUser(mockUser)
    }
}