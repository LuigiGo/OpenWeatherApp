package com.exam.openweatherapp.features.weather.ui.screens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.openweatherapp.databinding.ListItemBinding
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse

class SavedWeatherAdapter : RecyclerView.Adapter<SavedWeatherViewHolder>() {

    private var weatherResponses: ArrayList<WeatherResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedWeatherViewHolder {
        val context = parent.context
        val view = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SavedWeatherViewHolder(view, context)
    }

    override fun getItemCount(): Int = weatherResponses.size

    override fun onBindViewHolder(holder: SavedWeatherViewHolder, position: Int) {
        val weather = weatherResponses[position]
        holder.bindViews(weather)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(weatherResponses: List<WeatherResponse>) {
        with(this.weatherResponses) {
            clear()
            addAll(weatherResponses)
            notifyDataSetChanged()
        }

    }
}