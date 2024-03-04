package com.exam.openweatherapp.features.registration.data.repository.datasourceImpl

import com.exam.openweatherapp.features.registration.data.db.UserDao
import com.exam.openweatherapp.features.registration.data.model.User
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


internal class RegistrationLocalDataSourceImplTest {

    private lateinit var mockUserDao: UserDao
    private lateinit var mockUser: User

    @Before
    fun setup() {
        mockUserDao = mock(UserDao::class.java)
        mockUser = mock(User::class.java)
    }

    @Test
    fun saveUser() = runBlocking {
        val registrationLocalDataSourceImpl = RegistrationLocalDataSourceImpl(mockUserDao)
        registrationLocalDataSourceImpl.saveUser(mockUser)

        with(mockUser) {
            assertThat(this).isNotNull()
            assertThat(this).isInstanceOf(User::class.java)
            assertThat(mockUserDao).isInstanceOf(UserDao::class.java)
        }
        verify(mockUserDao, times(1)).saveUser(mockUser)
    }

    @Test
    fun getUsers() {
        `when`(mockUserDao.getUsers(anyString())).thenReturn(mockUser)
        val registrationLocalDSImpl = RegistrationLocalDataSourceImpl(mockUserDao)
        val result = registrationLocalDSImpl.getUsers(mockUser)

        with(result) {
            assertThat(this).isInstanceOf(User::class.java)
            assertThat(mockUser).isInstanceOf(User::class.java)
            assertThat(mockUserDao).isInstanceOf(UserDao::class.java)
        }
        verify(mockUserDao, atMostOnce()).getUsers(anyString())

    }
}