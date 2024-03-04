package com.exam.openweatherapp.features.weather.ui.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.exam.openweatherapp.R
import com.exam.openweatherapp.core.utils.helpers.getBackgroundByTime
import com.exam.openweatherapp.core.utils.helpers.getBackgroundColorByTime
import com.exam.openweatherapp.databinding.ActivityMainBinding
import com.exam.openweatherapp.features.login.ui.screens.LoginActivity
import com.exam.openweatherapp.features.weather.data.model.request.WeatherRequest
import com.exam.openweatherapp.features.weather.ui.viewmodel.WeatherViewModel
import com.exam.openweatherapp.features.weather.ui.viewmodel.WeatherViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: WeatherViewModelFactory
    lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]
        setContentView(binding.root)
        initViews()
        initNavController()
    }

    private fun initViews() {
        with(binding) {
            supportActionBar?.setBackgroundDrawable(getBackgroundColorByTime())
            supportActionBar?.elevation = 0.0F
            llMainBackground.background = getBackgroundByTime()
        }
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        with(binding) {
            bottomNav.setupWithNavController(navController)
        }


    }

    private fun fetchCurrentWeather(location: Location) {
        val request = WeatherRequest(location.latitude, location.longitude)
        viewModel.getCurrentWeather(request)
    }

    private fun observeLocationService() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000, 5f
            ) {
                fetchCurrentWeather(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeLocationService()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                viewModel.clearSharedPref()

                val i = Intent(this, LoginActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)

    }
}