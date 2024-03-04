package com.exam.openweatherapp.core.utils.helpers

fun formatString(vararg strings: String): String {
    var result = ""
    for (string in strings) {
        result += string
    }
    return result
}