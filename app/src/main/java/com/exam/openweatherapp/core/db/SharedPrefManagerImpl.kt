package com.exam.openweatherapp.core.db

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.exam.openweatherapp.BuildConfig
import com.exam.openweatherapp.core.utils.helpers.StringConst.Companion.PREF_KEY_EMAIL
import com.exam.openweatherapp.core.utils.helpers.StringConst.Companion.PREF_KEY_FIRSTNAME
import com.exam.openweatherapp.core.utils.helpers.StringConst.Companion.PREF_KEY_LASTNAME
import com.exam.openweatherapp.features.registration.data.model.User

class SharedPrefManagerImpl(context: Context) : SharedPrefManagerI {

    private var sharedPreferences: SharedPreferences

    init {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            BuildConfig.PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putString(PREF_KEY_FIRSTNAME, user.firstname)
            putString(PREF_KEY_LASTNAME, user.lastname)
            putString(PREF_KEY_EMAIL, user.email)
            apply()
        }
    }

    override fun getUser(): User {
        val firstname = sharedPreferences.getString(PREF_KEY_FIRSTNAME, "")
        val lastname = sharedPreferences.getString(PREF_KEY_LASTNAME, "")
        val email = sharedPreferences.getString(PREF_KEY_EMAIL, "")
        return User(firstname = firstname, lastname = lastname, email = email)
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }


}