package com.exam.openweatherapp.features.registration.data.repository.datasourceImpl

import com.exam.openweatherapp.features.registration.data.db.UserDao
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.data.repository.datasource.RegistrationLocalDataSource

class RegistrationLocalDataSourceImpl(
    private val userDao: UserDao,
) : RegistrationLocalDataSource {
    override suspend fun saveUser(user: User) {
        userDao.saveUser(user)
    }

    override fun getUsers(user: User): User? {
        return userDao.getUsers(user.email ?: "")
    }
}