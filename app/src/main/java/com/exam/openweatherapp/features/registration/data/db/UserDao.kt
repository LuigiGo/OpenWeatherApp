package com.exam.openweatherapp.features.registration.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exam.openweatherapp.features.registration.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)

    @Query("SELECT * FROM tbl_users WHERE email=:email")
    fun getUsers(email: String): User?
}