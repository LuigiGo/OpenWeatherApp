package com.exam.openweatherapp.features.registration.domain.usecase

import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.data.repository.RegistrationRepositoryImpl
import com.exam.openweatherapp.features.registration.domain.repository.RegistrationRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

internal class GetUsersUseCaseTest {

    private lateinit var mockRegistrationRepository: RegistrationRepository
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockRegistrationRepository = Mockito.mock(RegistrationRepositoryImpl::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun execute() {
        Mockito.`when`(mockRegistrationRepository.getUsers(mockUser)).thenReturn(mockUser)
        val getUsersUseCase = GetUsersUseCase(mockRegistrationRepository)
        val result = getUsersUseCase.execute(mockUser)

        with(result) {
            assertThat(this).isInstanceOf(User::class.java)
            assertThat(mockUser).isInstanceOf(User::class.java)
            assertThat(mockRegistrationRepository).isInstanceOf(RegistrationRepository::class.java)
        }
        verify(mockRegistrationRepository, times(1)).getUsers(mockUser)
    }
}