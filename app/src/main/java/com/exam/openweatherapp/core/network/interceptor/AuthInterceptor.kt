package com.exam.openweatherapp.core.network.interceptor

import android.app.Application
import com.exam.openweatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val app: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val url =
            chain.request().url.newBuilder().addQueryParameter("appid", BuildConfig.API_KEY).build()

        val request = chain.request().newBuilder().url(url).build()

        return chain.proceed(request)
    }

}