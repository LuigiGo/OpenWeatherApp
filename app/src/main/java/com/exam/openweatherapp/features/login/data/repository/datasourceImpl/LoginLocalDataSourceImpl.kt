package com.exam.openweatherapp.features.login.data.repository.datasourceImpl

import com.exam.openweatherapp.features.login.data.repository.datasource.LoginLocalDataSource
import com.exam.openweatherapp.features.registration.data.db.UserDao
import com.exam.openweatherapp.features.registration.data.model.User

class LoginLocalDataSourceImpl(
    private val userDao: UserDao,
) : LoginLocalDataSource {
    override fun getUsers(user: User): User? {
        return userDao.getUsers(user.email ?: "")
    }

}