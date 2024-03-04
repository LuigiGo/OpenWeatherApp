package com.exam.openweatherapp.features.login.data.repository.datasource

import com.exam.openweatherapp.features.registration.data.model.User

interface LoginLocalDataSource {

    fun getUsers(user: User): User?

}