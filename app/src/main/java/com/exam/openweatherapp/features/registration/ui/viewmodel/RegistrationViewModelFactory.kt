package com.exam.openweatherapp.features.registration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegistrationViewModelFactory(
    private val registrationViewModel: RegistrationViewModel,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return registrationViewModel as T
    }
}