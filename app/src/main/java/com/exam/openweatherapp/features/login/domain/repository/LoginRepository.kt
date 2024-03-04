package com.exam.openweatherapp.features.login.domain.repository

import com.exam.openweatherapp.features.registration.data.model.User

interface LoginRepository {

    fun saveUserToPref(user: User)

    fun getUserFromPref(): User
}