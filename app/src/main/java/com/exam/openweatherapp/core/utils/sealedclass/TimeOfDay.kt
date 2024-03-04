package com.exam.openweatherapp.core.utils.sealedclass

sealed class TimeOfDay {
    object Dawn : TimeOfDay()
    object Morning : TimeOfDay()
    object Night : TimeOfDay()
}
