package com.exam.openweatherapp.features.registration.domain.repository

import com.exam.openweatherapp.features.registration.data.model.User

interface RegistrationRepository {

    suspend fun saveUser(user: User)

    fun getUsers(user: User): User?

}