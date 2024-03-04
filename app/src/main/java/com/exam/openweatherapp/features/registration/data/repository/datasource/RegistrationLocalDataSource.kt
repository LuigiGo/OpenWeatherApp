package com.exam.openweatherapp.features.registration.data.repository.datasource

import com.exam.openweatherapp.features.registration.data.model.User

interface RegistrationLocalDataSource {

    suspend fun saveUser(user: User)

    fun getUsers(user: User): User?

}