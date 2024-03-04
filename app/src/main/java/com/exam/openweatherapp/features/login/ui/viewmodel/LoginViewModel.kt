package com.exam.openweatherapp.features.login.ui.viewmodel

import androidx.lifecycle.*
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.login.domain.usecase.GetUserFromPrefUseCase
import com.exam.openweatherapp.features.login.domain.usecase.SaveUserToPrefUseCase
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val saveUserToPrefUseCase: SaveUserToPrefUseCase,
    private val getUserFromPrefUseCase: GetUserFromPrefUseCase,
) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

     val _isValidUser: MutableLiveData<ResponseHandler<Boolean>> = MutableLiveData()
    val isValidUser = _isValidUser.distinctUntilChanged()

    private val _user: MutableLiveData<ResponseHandler<User>> = MutableLiveData()
    val user: LiveData<ResponseHandler<User>> = _user.distinctUntilChanged()


    fun login(user: User) = viewModelScope.launch(IO) {
        val registeredUser = getUser(user)
        val email = user.email
        val password = user.password

        if (registeredUser != null) {
            if (email == registeredUser.email && password == registeredUser.password) {
                _isValidUser.postValue(ResponseHandler.Success(true))
                saveUserToPrefUseCase.execute(registeredUser)
            } else {
                _isValidUser.postValue(ResponseHandler.Error("Unable to sign in. Please email and password."))
            }
        } else {
            _isValidUser.postValue(ResponseHandler.Error("Unable to sign in. Please check email or register an account."))
        }
    }

    private fun getUser(user: User): User? = getUsersUseCase.execute(user)

    fun getUserFromPref() {
        val user = getUserFromPrefUseCase.execute()
        _user.postValue(ResponseHandler.Success(user))

    }
}