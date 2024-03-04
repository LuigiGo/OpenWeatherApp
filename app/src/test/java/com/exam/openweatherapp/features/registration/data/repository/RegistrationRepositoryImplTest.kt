package com.exam.openweatherapp.features.registration.data.repository

import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.data.repository.datasource.RegistrationLocalDataSource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

internal class RegistrationRepositoryImplTest {

    private lateinit var mockRegistrationLocalDataSource: RegistrationLocalDataSource
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockRegistrationLocalDataSource = Mockito.mock(RegistrationLocalDataSource::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun saveUser() = runBlocking {
        val registrationRepository = RegistrationRepositoryImpl(mockRegistrationLocalDataSource)
        registrationRepository.saveUser(mockUser)

        with(mockUser) {
            assertThat(this).isNotNull()
            assertThat(this).isInstanceOf(User::class.java)
            assertThat(mockRegistrationLocalDataSource).isInstanceOf(RegistrationLocalDataSource::class.java)
        }
        Mockito.verify(mockRegistrationLocalDataSource, Mockito.times(1)).saveUser(mockUser)
    }

    @Test
    fun getUsers() {
        Mockito.`when`(mockRegistrationLocalDataSource.getUsers(mockUser)).thenReturn(mockUser)

        val registrationRepository = RegistrationRepositoryImpl(mockRegistrationLocalDataSource)
        val result = registrationRepository.getUsers(mockUser)

        with(result) {
            assertThat(this).isNotNull()
            assertThat(this).isEqualTo(mockUser)
            assertThat(this).isInstanceOf(User::class.java)
            assertThat(mockUser).isInstanceOf(User::class.java)
            assertThat(mockRegistrationLocalDataSource).isInstanceOf(RegistrationLocalDataSource::class.java)
        }
        Mockito.verify(mockRegistrationLocalDataSource, Mockito.atMostOnce()).getUsers(mockUser)
    }
}