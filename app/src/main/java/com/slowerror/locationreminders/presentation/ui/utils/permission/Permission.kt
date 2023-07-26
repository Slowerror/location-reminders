package com.slowerror.locationreminders.presentation.ui.utils.permission
import android.Manifest.permission.*
import java.lang.IllegalArgumentException

sealed class Permission(vararg val permissions: String) {

    object Location : Permission(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)

    companion object {
        fun from(permission: String) = when (permission) {
            ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION -> Location
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }

}