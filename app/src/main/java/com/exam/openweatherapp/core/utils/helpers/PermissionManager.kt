package com.exam.openweatherapp.core.utils.helpers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager {

    companion object {
        private const val TAG = "PermissionManager"
        const val ALL_PERMISSION_REQUEST_CODE = 99
        const val BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 66
    }

    private var permissions: Array<String> = arrayOf()

    fun setPermissions(permissions: Array<String>) {
        this.permissions = permissions
    }

    fun checkPermissions(activity: Activity) {
        if (!areAllPermissionsGranted(activity)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                requestPermission(activity)
            } else {
                requestPermission(activity)
            }
        }
    }

    fun checkDeniedPermission(activity: Activity): String {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    activity, permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return permission
            }
        }
        return ""
    }

    private fun areAllPermissionsGranted(activity: Activity) = permissions.all {
        hasPermission(activity, it)
    }

    private fun hasPermission(activity: Activity, permission: String) =
        ContextCompat.checkSelfPermission(
            activity, permission
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity, permissions, ALL_PERMISSION_REQUEST_CODE
        )
    }


}