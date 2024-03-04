package com.exam.openweatherapp.features.registration.domain.usecase

import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.domain.repository.RegistrationRepository

class GetUsersUseCase(
    private val repository: RegistrationRepository,
) {

    fun execute(user: User): User? = repository.getUsers(user)
}