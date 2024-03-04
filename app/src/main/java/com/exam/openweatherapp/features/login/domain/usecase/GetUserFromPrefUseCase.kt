package com.exam.openweatherapp.features.login.domain.usecase

import com.exam.openweatherapp.features.login.domain.repository.LoginRepository
import com.exam.openweatherapp.features.registration.data.model.User

class GetUserFromPrefUseCase(
    private val repository: LoginRepository,
) {

    fun execute(): User = repository.getUserFromPref()
}