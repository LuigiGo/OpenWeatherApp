package com.exam.openweatherapp.features.login.data.repository

import com.exam.openweatherapp.core.db.SharedPrefManagerI
import com.exam.openweatherapp.features.login.data.repository.datasource.LoginLocalDataSource
import com.exam.openweatherapp.features.registration.data.model.User
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

internal class LoginRepositoryImplTest {

    private lateinit var mockLoginLocalDataSource: LoginLocalDataSource
    private lateinit var mockSharedPrefManagerI: SharedPrefManagerI
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockLoginLocalDataSource = Mockito.mock(LoginLocalDataSource::class.java)
        mockSharedPrefManagerI = Mockito.mock(SharedPrefManagerI::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun saveUserToPref() {
        val loginRepositoryImpl =
            LoginRepositoryImpl(mockLoginLocalDataSource, mockSharedPrefManagerI)
        loginRepositoryImpl.saveUserToPref(mockUser)

        assertThat(mockUser).isInstanceOf(User::class.java)
        assertThat(mockLoginLocalDataSource).isInstanceOf(LoginLocalDataSource::class.java)
        assertThat(mockSharedPrefManagerI).isInstanceOf(SharedPrefManagerI::class.java)
        verify(mockSharedPrefManagerI, times(1)).saveUser(mockUser)
    }

    @Test
    fun getUserFromPref() {
        Mockito.`when`(mockSharedPrefManagerI.getUser()).thenReturn(mockUser)
        val loginRepositoryImpl =
            LoginRepositoryImpl(mockLoginLocalDataSource, mockSharedPrefManagerI)
        val result = loginRepositoryImpl.getUserFromPref()

        assertThat(result).isInstanceOf(User::class.java)
        assertThat(mockUser).isInstanceOf(User::class.java)
        assertThat(mockLoginLocalDataSource).isInstanceOf(LoginLocalDataSource::class.java)
        assertThat(mockSharedPrefManagerI).isInstanceOf(SharedPrefManagerI::class.java)
        verify(mockSharedPrefManagerI, times(1)).getUser()



    }
}