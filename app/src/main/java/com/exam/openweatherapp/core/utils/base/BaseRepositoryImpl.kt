package com.exam.openweatherapp.core.utils.base

import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.features.weather.data.model.response.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

open class BaseRepositoryImpl {

    companion object {
        private const val TAG = "BaseRepositoryImpl"
    }

    protected suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): ResponseHandler<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return ResponseHandler.Error(t.message)
        }

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return ResponseHandler.Success(result)
            }
        } else {
            val error = response.errorBody()?.charStream()?.readText()
            val errorResponse = Gson().fromJson(error, ErrorResponse::class.java)
            return ResponseHandler.Error(errorResponse.message)
        }
        return ResponseHandler.Error("Oops! Something went wrong.")

    }
}
