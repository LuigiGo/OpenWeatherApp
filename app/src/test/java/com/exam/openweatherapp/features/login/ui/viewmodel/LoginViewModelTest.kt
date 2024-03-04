package com.exam.openweatherapp.features.login.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exam.openweatherapp.features.login.domain.usecase.GetUserFromPrefUseCase
import com.exam.openweatherapp.features.login.domain.usecase.SaveUserToPrefUseCase
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.domain.usecase.GetUsersUseCase
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockGetUsersUseCase: GetUsersUseCase

    @Mock
    private lateinit var mockSaveUserToPrefUseCase: SaveUserToPrefUseCase

    @Mock
    private lateinit var mockGetUserFromPrefUseCase: GetUserFromPrefUseCase

    @Mock
    private lateinit var mockUser: User


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        mockGetUsersUseCase = mock(GetUsersUseCase::class.java)
        mockSaveUserToPrefUseCase = mock(SaveUserToPrefUseCase::class.java)
        mockGetUserFromPrefUseCase = mock(GetUserFromPrefUseCase::class.java)
        mockUser = mock(User::class.java)
    }

    @Test
    fun login_savedUserDataToPref() {
        `when`(mockUser.email).thenReturn("test")
        `when`(mockUser.password).thenReturn("test")
        `when`(mockGetUsersUseCase.execute(mockUser)).thenReturn(mockUser)

        val loginViewModel = LoginViewModel(
            mockGetUsersUseCase,
            mockSaveUserToPrefUseCase,
            mockGetUserFromPrefUseCase,
        )
        loginViewModel.login(mockUser)

        assertThat(mockUser).isNotNull()
        assertThat(mockSaveUserToPrefUseCase).isNotNull()
        verify(mockSaveUserToPrefUseCase, times(1)).execute(mockUser)
    }

    @Test
    fun login_unableToSignIn_userNotRegistered() {
        `when`(mockUser.email).thenReturn("test")
        `when`(mockUser.password).thenReturn("test")
        `when`(mockGetUsersUseCase.execute(mockUser)).thenReturn(null)

        val loginViewModel = LoginViewModel(
            mockGetUsersUseCase,
            mockSaveUserToPrefUseCase,
            mockGetUserFromPrefUseCase,
        )
        loginViewModel.login(mockUser)

        assertThat(mockUser).isNotNull()
        assertThat(mockGetUsersUseCase).isNotNull()
        assertThat(mockSaveUserToPrefUseCase).isNotNull()
        verifyNoInteractions(mockSaveUserToPrefUseCase)
    }

    @Test
    fun login_unableToSignIn_incorrectCredentials() {
        val loginViewModel = LoginViewModel(
            mockGetUsersUseCase,
            mockSaveUserToPrefUseCase,
            mockGetUserFromPrefUseCase,
        )
        val user = User(1, "test1", "test1", "test1", "test")
        loginViewModel.login(user)

        assertThat(mockUser).isNotNull()
        assertThat(mockGetUsersUseCase).isNotNull()
        assertThat(mockSaveUserToPrefUseCase).isNotNull()
        verifyNoInteractions(mockSaveUserToPrefUseCase)
    }

    @Test
    fun getUserFromPref() {
        val loginViewModel = LoginViewModel(
            mockGetUsersUseCase,
            mockSaveUserToPrefUseCase,
            mockGetUserFromPrefUseCase,
        )
        loginViewModel.getUserFromPref()

        verify(mockGetUserFromPrefUseCase, times(1)).execute()
    }
}