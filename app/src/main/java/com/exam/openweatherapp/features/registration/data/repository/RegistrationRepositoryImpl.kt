package com.exam.openweatherapp.features.registration.data.repository

import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.data.repository.datasource.RegistrationLocalDataSource
import com.exam.openweatherapp.features.registration.domain.repository.RegistrationRepository

class RegistrationRepositoryImpl(
    private val localDataSource: RegistrationLocalDataSource,
) : RegistrationRepository {

    override suspend fun saveUser(user: User) {
        localDataSource.saveUser(user)
    }

    override fun getUsers(user: User): User? {
        return localDataSource.getUsers(user)
    }

}