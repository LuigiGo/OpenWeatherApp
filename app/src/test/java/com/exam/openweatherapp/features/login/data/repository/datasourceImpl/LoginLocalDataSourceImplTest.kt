package com.exam.openweatherapp.features.login.data.repository.datasourceImpl

import com.exam.openweatherapp.features.registration.data.db.UserDao
import com.exam.openweatherapp.features.registration.data.model.User
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

internal class LoginLocalDataSourceImplTest {

    private lateinit var mockUserDao: UserDao
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        mockUserDao = Mockito.mock(UserDao::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun getUsers() {
        Mockito.`when`(mockUserDao.getUsers(anyString())).thenReturn(mockUser)
        val loginLocalDataSource = LoginLocalDataSourceImpl(mockUserDao)
        val result = loginLocalDataSource.getUsers(mockUser)

        with(result) {
            assertThat(this).isNotNull()
            assertThat(mockUser).isInstanceOf(User::class.java)
            assertThat(mockUserDao).isInstanceOf(UserDao::class.java)
        }

        verify(mockUserDao, times(1)).getUsers(anyString())
    }
}