package com.exam.openweatherapp.core.utils.helpers

import com.exam.openweatherapp.core.utils.sealedclass.TimeOfDay
import java.text.SimpleDateFormat
import java.util.*


fun Int.convertToHourAndSeconds(): String {
    val date = Date(this.toLong() * 1000L)
    val formatter = SimpleDateFormat("hh:mm aa", Locale.UK)
    formatter.timeZone = TimeZone.getTimeZone("GMT+8")
    return formatter.format(date).uppercase()
}

fun Double.convertDoubleToString() = this.toInt().toString()

fun getDateToday(): String {
    val date = Date()
    val formatter = SimpleDateFormat("EEEE, MMM dd yyyy", Locale.UK)
    formatter.timeZone = TimeZone.getTimeZone("GMT+8")
    return formatter.format(date)
}

fun getTimeOfTheDay(): TimeOfDay {
    val calendar = Calendar.getInstance()
    return when (calendar[Calendar.HOUR_OF_DAY]) {
        in 0..8 -> TimeOfDay.Dawn
        in 9..17 -> TimeOfDay.Morning
        in 18..24 -> TimeOfDay.Night
        else -> TimeOfDay.Morning
    }
}