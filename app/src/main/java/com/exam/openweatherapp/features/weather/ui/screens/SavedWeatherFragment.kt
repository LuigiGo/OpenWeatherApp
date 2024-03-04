package com.exam.openweatherapp.features.weather.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.openweatherapp.R
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.databinding.FragmentSavedWeatherBinding
import com.exam.openweatherapp.features.weather.ui.viewmodel.WeatherViewModel

class SavedWeatherFragment : Fragment() {

    private lateinit var binding: FragmentSavedWeatherBinding
    private var adapter = SavedWeatherAdapter()

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedWeatherBinding.bind(view)

        viewModel = (activity as WeatherActivity).viewModel
        initViews()
        initDataObservers()
    }

    private fun initViews() {
        with(binding) {
            rvWeather.apply {
                adapter = this@SavedWeatherFragment.adapter
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    private fun initDataObservers() {
        viewModel.getSavedWeather()
        with(viewModel) {
            savedWeathers.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ResponseHandler.Success -> {
                        result.data?.let { adapter.setData(it) }
                    }
                    else -> Unit
                }
            }
        }
    }

}