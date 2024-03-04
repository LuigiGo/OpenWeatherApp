package com.exam.openweatherapp.features.weather.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exam.openweatherapp.R
import com.exam.openweatherapp.core.utils.helpers.*
import com.exam.openweatherapp.databinding.FragmentCurrentWeatherBinding
import com.exam.openweatherapp.features.weather.data.model.response.WeatherResponse
import com.exam.openweatherapp.features.weather.ui.viewmodel.WeatherViewModel
import com.labters.lottiealertdialoglibrary.DialogTypes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding

    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as WeatherActivity).viewModel
        binding = FragmentCurrentWeatherBinding.bind(view)

        initDataObservers()
        viewModel.getUserFromPref()
    }

    private fun initDataObservers() {
        with(viewModel) {
            currentWeatherResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ResponseHandler.Success -> {
                        response.data?.let { weather ->
                            showWeatherInfo(weather)
                        }
                    }
                    is ResponseHandler.Error -> {
                        activity?.let {
                            dialogHelper.showDialog(
                                it, response.errorMessage, dialogTypes = DialogTypes.TYPE_ERROR
                            )
                        }
                    }
                    is ResponseHandler.Loading -> {

                    }
                }
            }

            viewModel.user.observe(viewLifecycleOwner) { user ->
                when (user) {
                    is ResponseHandler.Success -> {
                        binding.tvGreeting.text =
                            formatString("Hi, ", user.data?.firstname ?: "Guest", "!")
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun showWeatherInfo(weatherResponse: WeatherResponse) {
        val temp = weatherResponse.main.temp.convertDoubleToString()
        val city = weatherResponse.name
        val country = weatherResponse.sys.country
        val sunrise = weatherResponse.sys.sunrise.convertToHourAndSeconds()
        val sunset = weatherResponse.sys.sunset.convertToHourAndSeconds()
        val dateToday = getDateToday()
        val weatherCondition = weatherResponse.weather

        with(binding) {
            activity.also {
                it.loadImage(getWeatherIcon(weatherCondition), imgWeatherType)
                it.loadImage(R.drawable.ic_sunrise, imgSunrise)
                it.loadImage(R.drawable.ic_sunset, imgSunset)
            }

            tvTemp.text = formatString(temp, "°")
            tvPlace.text = formatString(city, ", ", country)
            tvSunrise.text = sunrise
            tvSunset.text = sunset
            tvDateToday.text = dateToday


        }
    }
}