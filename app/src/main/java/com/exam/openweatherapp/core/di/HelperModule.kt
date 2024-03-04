package com.exam.openweatherapp.core.di

import com.exam.openweatherapp.core.utils.helpers.DataConverter
import com.exam.openweatherapp.core.utils.helpers.DialogHelper
import com.exam.openweatherapp.core.utils.helpers.PermissionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HelperModule {

    @Singleton
    @Provides
    fun providesDialogHelper(): DialogHelper {
        return DialogHelper()
    }

    @Singleton
    @Provides
    fun providesPermissionManager(): PermissionManager {
        return PermissionManager()
    }

}