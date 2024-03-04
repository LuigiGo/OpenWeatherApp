package com.exam.openweatherapp.features.weather.ui.screens

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.exam.openweatherapp.R
import com.exam.openweatherapp.core.utils.helpers.*
import com.exam.openweatherapp.databinding.ListItemBinding
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse

class SavedWeatherViewHolder(
    private val binding: ListItemBinding,
    private val context: Context,
) : ViewHolder(binding.root) {
    fun bindViews(weatherResponse: WeatherResponse) {
        val city = weatherResponse.name
        val country = weatherResponse.sys.country
        val sunrise = weatherResponse.sys.sunrise.convertToHourAndSeconds()
        val sunset = weatherResponse.sys.sunset.convertToHourAndSeconds()
        val temp = weatherResponse.main.temp.convertDoubleToString()
        val weatherCondition = weatherResponse.weather

        with(binding) {
            tvPlace.text = formatString(city, ", ", country)
            tvSunrise.text = formatString(context.getString(R.string.sunrise_with_colon), sunrise)
            tvSunset.text = formatString(context.getString(R.string.sunset_with_colon), sunset)
            tvTemp.text = formatString(temp, "°")

            context.also {
                it.loadImage(getWeatherIcon(weatherCondition), imgWeatherType)
            }

        }
        this.itemView.setOnClickListener { }
    }
}