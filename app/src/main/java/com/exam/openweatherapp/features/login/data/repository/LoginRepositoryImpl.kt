package com.exam.openweatherapp.features.login.data.repository

import com.exam.openweatherapp.core.db.SharedPrefManagerI
import com.exam.openweatherapp.features.login.data.repository.datasource.LoginLocalDataSource
import com.exam.openweatherapp.features.login.domain.repository.LoginRepository
import com.exam.openweatherapp.features.registration.data.model.User

class LoginRepositoryImpl(
    private val localDataSource: LoginLocalDataSource,
    private val sharedPrefManager: SharedPrefManagerI,
) : LoginRepository {

    override fun saveUserToPref(user: User) {
        sharedPrefManager.saveUser(user)
    }

    override fun getUserFromPref(): User {
        return sharedPrefManager.getUser()
    }
}