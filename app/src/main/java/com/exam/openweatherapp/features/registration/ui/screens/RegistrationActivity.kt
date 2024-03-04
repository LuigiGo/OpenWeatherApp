package com.exam.openweatherapp.features.registration.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.exam.openweatherapp.R
import com.exam.openweatherapp.core.utils.helpers.DialogHelper
import com.exam.openweatherapp.core.utils.helpers.ResponseHandler
import com.exam.openweatherapp.core.utils.helpers.isValidEmail
import com.exam.openweatherapp.databinding.ActivityRegistrationBinding
import com.exam.openweatherapp.features.login.ui.screens.LoginActivity
import com.exam.openweatherapp.features.registration.data.model.User
import com.exam.openweatherapp.features.registration.ui.viewmodel.RegistrationViewModel
import com.exam.openweatherapp.features.registration.ui.viewmodel.RegistrationViewModelFactory
import com.labters.lottiealertdialoglibrary.DialogTypes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var factory: RegistrationViewModelFactory
    lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory)[RegistrationViewModel::class.java]
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        supportActionBar?.elevation = 0.0F
    }

    private fun initListeners() {
        with(binding) {
            btnSignUp.setOnClickListener {
                val firstname = edtFirstname.text.toString()
                val lastname = edtLastname.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val confirmPassword = edtConfirmPassword.text.toString()

                if (!email.isValidEmail()) {
                    dialogHelper.showDialog(
                        this@RegistrationActivity,
                        message = getString(R.string.err_msg_invalid_email),
                        dialogTypes = DialogTypes.TYPE_ERROR
                    )
                } else if (password != confirmPassword) {
                    dialogHelper.showDialog(
                        this@RegistrationActivity,
                        message = getString(R.string.err_msg_password_not_matched),
                        dialogTypes = DialogTypes.TYPE_ERROR
                    )
                } else {
                    val user = User(
                        firstname = firstname,
                        lastname = lastname,
                        email = email,
                        password = password
                    )
                    viewModel.registerUser(user)

                }
            }
        }
    }

    private fun initObservers() {
        viewModel.isEmailExist.observe(this) { result ->
            when (result) {
                is ResponseHandler.Success -> {
                    dialogHelper.showDialog(
                        this,
                        getString(R.string.success),
                        getString(R.string.success_msg_account_registered),
                        getString(R.string.okay),
                        DialogTypes.TYPE_SUCCESS
                    ) {
                        val i = Intent(this, LoginActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(i)
                    }
                }
                is ResponseHandler.Error -> {
                    dialogHelper.showDialog(
                        this,
                        message = result.errorMessage,
                        okButtonTitle = getString(R.string.okay),
                        dialogTypes = DialogTypes.TYPE_WARNING
                    )
                }
                is ResponseHandler.Loading -> {}
            }


        }
    }
}