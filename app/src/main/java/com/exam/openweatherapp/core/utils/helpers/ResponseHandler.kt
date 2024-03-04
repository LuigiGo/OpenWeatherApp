package com.exam.openweatherapp.core.utils.helpers

sealed class ResponseHandler<T>(
    val data: T? = null,
    val errorMessage: String? = null,
) {
    class Success<T>(data: T?) : ResponseHandler<T>(data)
    class Loading<T>(data: T? = null) : ResponseHandler<T>(data)
    class Error<T>(message: String?, data: T? = null) : ResponseHandler<T>(data, message)
}
