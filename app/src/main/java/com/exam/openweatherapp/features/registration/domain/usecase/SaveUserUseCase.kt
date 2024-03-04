package com.exam.openweatherapp.features.registration.domain.usecase

import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.domain.repository.RegistrationRepository

class SaveUserUseCase(
    private val repository: RegistrationRepository,
) {
    suspend fun execute(user: User) = repository.saveUser(user)
}