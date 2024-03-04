package com.exam.openweatherapp.features.registration.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.domain.usecase.GetUsersUseCase
import com.exam.openweatherapp.features.registration.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    companion object {
        private const val TAG = "RegistrationViewModel"
    }

    private var _isEmailExist: MutableLiveData<ResponseHandler<Boolean>> = MutableLiveData()
    val isEmailExist = _isEmailExist.distinctUntilChanged()

    fun registerUser(user: User) = viewModelScope.launch(IO) {
        val registeredUsers = getUsers(user)
        if (user.firstname.isNullOrEmpty() || user.lastname.isNullOrEmpty() || user.email.isNullOrEmpty() || user.password.isNullOrEmpty()) {
            _isEmailExist.postValue(ResponseHandler.Error("Unable to sign up. Please fill up required fields."))
        } else if (registeredUsers != null) {
            _isEmailExist.postValue(ResponseHandler.Error("The email already registered. Please use another email."))
        } else {
            saveUserUseCase.execute(user)
            _isEmailExist.postValue(ResponseHandler.Success(true))

        }
    }

    private fun getUsers(user: User): User? = getUsersUseCase.execute(user)

    fun getIsEmailExistValue(): MutableLiveData<ResponseHandler<Boolean>> = _isEmailExist
}