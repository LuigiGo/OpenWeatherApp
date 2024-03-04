package com.exam.openweatherapp.core.utils.helpers

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.exam.openweatherapp.R
import com.exam.openweatherapp.core.utils.sealedclass.TimeOfDay
import com.exam.openweatherapp.features.weather.data.model.response.Weather

fun Activity?.loadImage(drawableId: Int, view: ImageView) {
    this?.let {
        val drawable = ContextCompat.getDrawable(it, drawableId)
        Glide.with(it).load(drawable).into(view)
    }
}

fun Context?.loadImage(drawableId: Int, view: ImageView) {
    this?.let {
        val drawable = ContextCompat.getDrawable(it, drawableId)
        Glide.with(it).load(drawable).into(view)
    }
}

fun getWeatherIcon(weatherCondition: List<Weather>?): Int {
    return when (getTimeOfTheDay()) {
        is TimeOfDay.Dawn -> R.drawable.img_moon
        is TimeOfDay.Night -> R.drawable.img_moon
        else -> {
            val isRaining = weatherCondition?.any { it.main == "Rain" } ?: false
            if (isRaining) R.drawable.img_raining else R.drawable.img_morning
        }
    }
}

fun Context?.getBackgroundByTime(): Drawable? {
    val drawableId = when (getTimeOfTheDay()) {
        is TimeOfDay.Dawn -> R.drawable.bg_midnight
        is TimeOfDay.Night -> R.drawable.bg_midnight
        else -> R.drawable.bg_morning
    }
    return this?.let { ContextCompat.getDrawable(it, drawableId) }
}

fun Context?.getBackgroundColorByTime(): Drawable? {
    val colorId = when (getTimeOfTheDay()) {
        is TimeOfDay.Dawn -> R.color.tertiary
        is TimeOfDay.Night -> R.color.tertiary
        else -> R.color.secondary
    }
    return this?.let { ColorDrawable(resources.getColor(colorId)) }
}

fun EditText.validateEditTextValue(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            action.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    })

}