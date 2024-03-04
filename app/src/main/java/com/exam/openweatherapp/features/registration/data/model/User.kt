package com.exam.openweatherapp.features.registration.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var email: String? = null,
    var password: String? = null,
)