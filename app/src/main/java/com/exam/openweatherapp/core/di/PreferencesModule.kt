package com.exam.openweatherapp.core.di

import android.app.Application
import com.exam.openweatherapp.core.db.SharedPrefManagerImpl
import com.exam.openweatherapp.core.db.SharedPrefManagerI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Singleton
    @Provides
    fun providesSharedPrefManager(app: Application): SharedPrefManagerI {
        return SharedPrefManagerImpl(app)

    }
}