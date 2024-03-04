package com.exam.openweatherapp.core.db

import com.exam.openweatherapp.features.registration.data.model.User

interface SharedPrefManagerI {

    fun saveUser(user: User)

    fun getUser(): User

    fun clear()
}