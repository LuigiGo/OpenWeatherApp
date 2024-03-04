package com.exam.openweatherapp.features.login.ui.screens

import android.Manifest
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.exam.openweatherapp.R
import com.exam.openweatherapp.core.utils.helpers.*
import com.exam.openweatherapp.core.utils.helpers.StringConst.Companion.KEY_PACKAGE
import com.exam.openweatherapp.databinding.ActivityLoginBinding
import com.exam.openweatherapp.features.login.ui.viewmodel.LoginViewModel
import com.exam.openweatherapp.features.login.ui.viewmodel.LoginViewModelFactory
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.ui.screens.RegistrationActivity
import com.exam.openweatherapp.features.weather.ui.screens.WeatherActivity
import com.labters.lottiealertdialoglibrary.DialogTypes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var factory: LoginViewModelFactory
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        initPermissions()
        initViews()
        initListeners()
        initObservers()
        viewModel.getUserFromPref()
    }


    private fun initPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        )
        permissionManager.setPermissions(permissions)
        permissionManager.checkPermissions(this)
    }

    private fun initViews() {
        supportActionBar?.elevation = 0.0F
        loadImage(R.drawable.img_logo, binding.imgLogo)
    }

    private fun initListeners() {
        with(binding) {

            btnSignIn.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@LoginActivity, Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    showPermissionDialog {
                        startActivity(
                            Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts(KEY_PACKAGE, this@LoginActivity.packageName, null),
                            ),
                        )
                    }
                    return@setOnClickListener
                }

                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val user = User(email = email, password = password)
                viewModel.login(user)
            }

            btnSignUp.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            }

            edtEmail.validateEditTextValue { value ->
                if (value.isValidEmail()) tilEmail.error = null else tilEmail.error =
                    getString(R.string.err_msg_invalid_email)
            }
        }
    }

    private fun initObservers() {
        viewModel.isValidUser.observe(this) { result ->
            when (result) {
                is ResponseHandler.Error -> {
                    dialogHelper.showDialog(
                        this,
                        message = result.errorMessage,
                        dialogTypes = DialogTypes.TYPE_WARNING,
                    )
                }
                is ResponseHandler.Loading -> {}
                is ResponseHandler.Success -> {
                    val i = Intent(this@LoginActivity, WeatherActivity::class.java)
                    i.addFlags(FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(i)
                }
            }
        }

        viewModel.user.observe(this) { user ->
            when (user) {
                is ResponseHandler.Success -> {
                    if (!user.data?.email.isNullOrEmpty()) {
                        val i = Intent(this, WeatherActivity::class.java)
                        i.addFlags(FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(i)
                    }
                }
                else -> Unit
            }
        }
    }

    private fun showPermissionDialog(callback: (() -> Unit?)? = null) {
        val deniedPermission = permissionManager.checkDeniedPermission(this)
        dialogHelper.showDialog(
            this,
            getString(R.string.header_permission_required),
            "This app needs the $deniedPermission, please accept to use location functionality.",
            dialogTypes = DialogTypes.TYPE_ERROR
        ) {
            callback?.invoke()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionManager.ALL_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(
                            this, Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        showPermissionDialog {
                            startActivity(
                                Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts(KEY_PACKAGE, this.packageName, null),
                                ),
                            )
                        }

                    } else {
                        showPermissionDialog {
                            permissionManager.checkPermissions(this)
                        }
                    }
                } else {
                    dialogHelper.showDialog(
                        this,
                        getString(R.string.header_permission_granted),
                        getString(R.string.success_location_enabled),
                        dialogTypes = DialogTypes.TYPE_SUCCESS
                    )
                }
                return
            }
        }
    }
}