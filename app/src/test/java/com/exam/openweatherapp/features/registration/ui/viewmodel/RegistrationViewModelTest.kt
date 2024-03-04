package com.exam.openweatherapp.features.registration.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.domain.usecase.GetUsersUseCase
import com.exam.openweatherapp.features.registration.domain.usecase.SaveUserUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class RegistrationViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockSaveUserUseCase: SaveUserUseCase
    private lateinit var mockGetUsersUseCase: GetUsersUseCase
    private lateinit var mockUser: User

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockSaveUserUseCase = Mockito.mock(SaveUserUseCase::class.java)
        mockGetUsersUseCase = Mockito.mock(GetUsersUseCase::class.java)
        mockUser = Mockito.mock(User::class.java)
    }

    @Test
    fun registerUser_unableToLogin() {
        val viewModel = RegistrationViewModel(mockSaveUserUseCase, mockGetUsersUseCase)
        viewModel.registerUser(mockUser)
        val isUserRegistered = viewModel.getIsEmailExistValue()

        with(mockUser) {
            assertThat(firstname).isNull()
            assertThat(lastname).isNull()
            assertThat(email).isNull()
            assertThat(password).isNull()
            assertThat(this).isInstanceOf(User::class.java)
            assertThat(isUserRegistered.value).isInstanceOf(ResponseHandler.Error::class.java)
            assertThat(isUserRegistered.value?.errorMessage).isEqualTo("Unable to sign up. Please fill up required fields.")
        }

        assertThat(mockGetUsersUseCase).isInstanceOf(GetUsersUseCase::class.java)
        assertThat(mockSaveUserUseCase).isInstanceOf(SaveUserUseCase::class.java)
        verify(mockGetUsersUseCase, times(1)).execute(mockUser)
    }

    @Test
    fun registerUser_emailAlreadyRegistered() {
        with(mockUser) {
            Mockito.`when`(firstname).thenReturn("test")
            Mockito.`when`(lastname).thenReturn("test")
            Mockito.`when`(email).thenReturn("test")
            Mockito.`when`(password).thenReturn("test")
            Mockito.`when`(mockGetUsersUseCase.execute(mockUser)).thenReturn(mockUser)
        }
        val viewModel = RegistrationViewModel(mockSaveUserUseCase, mockGetUsersUseCase)
        viewModel.registerUser(mockUser)

        with(mockUser) {
            assertThat(firstname).isNotNull()
            assertThat(lastname).isNotNull()
            assertThat(email).isNotNull()
            assertThat(password).isNotNull()
            assertThat(this).isInstanceOf(User::class.java)
        }

        assertThat(mockGetUsersUseCase).isInstanceOf(GetUsersUseCase::class.java)
        assertThat(mockSaveUserUseCase).isInstanceOf(SaveUserUseCase::class.java)
        verify(mockGetUsersUseCase, times(1)).execute(mockUser)
    }

    @Test
    fun registerUser_saveUserToDB() = runBlocking {
        with(mockUser) {
            Mockito.`when`(firstname).thenReturn("test")
            Mockito.`when`(lastname).thenReturn("test")
            Mockito.`when`(email).thenReturn("test")
            Mockito.`when`(password).thenReturn("test")
        }
        Mockito.`when`(mockGetUsersUseCase.execute(mockUser)).thenReturn(null)

        val viewModel = RegistrationViewModel(mockSaveUserUseCase, mockGetUsersUseCase)
        viewModel.registerUser(mockUser)

        with(mockUser) {
            assertThat(firstname).isNotEmpty()
            assertThat(lastname).isNotEmpty()
            assertThat(email).isNotEmpty()
            assertThat(password).isNotEmpty()
            assertThat(this).isNotNull()
        }

        assertThat(mockGetUsersUseCase).isNotNull()
        assertThat(mockGetUsersUseCase.execute(mockUser)).isNull()

        assertThat(mockSaveUserUseCase).isNotNull()
        verify(mockSaveUserUseCase, times(1)).execute(mockUser)
    }
}