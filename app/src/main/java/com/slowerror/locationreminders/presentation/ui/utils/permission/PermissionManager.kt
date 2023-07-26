package com.slowerror.locationreminders.presentation.ui.utils.permission

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.slowerror.locationreminders.R
import timber.log.Timber
import java.lang.ref.WeakReference

class PermissionManager private constructor(private val fragment: WeakReference<Fragment>) {

    private val requiredPermissions = mutableListOf<Permission>()
    private var titleDialog: String? = null
    private var rationaleDialog: String? = null
    private var callback: (Boolean) -> Unit = {}
    private var detailedCallback: (Map<Permission, Boolean>) -> Unit = {}

    private val permissionCheck =
        fragment.get()?.registerForActivityResult(RequestMultiplePermissions()) { result ->
            sendResultAndCleanUp(result)
        }


    companion object {
        fun from(fragment: Fragment) = PermissionManager(WeakReference(fragment))
    }

    fun request(vararg permission: Permission): PermissionManager {
        requiredPermissions.addAll(permission)
        Timber.i("request was called")
        Timber.i("requiredPermissions = $requiredPermissions")
        return this
    }

    fun titleAndRationale(title: String, description: String): PermissionManager {
        Timber.i("titleAndRationale was called")
        titleDialog = title
        rationaleDialog = description
        return this
    }

    fun checkPermission(callback: (Boolean) -> Unit) {
        Timber.i("checkPermission was called")
        this.callback = callback
        handlePermissionRequest()
    }

    private fun handlePermissionRequest() {
        fragment.get()?.let { fragment ->
            when {
                allPermissionGranted(fragment) -> {
                    Timber.i("handlePermissionRequest: allPermissionGranted was called")
                    sendPositiveResult()
                }
                shouldShowRationaleDialog(fragment) -> {
                    Timber.i("handlePermissionRequest: shouldShowRationaleDialog was called")
                    showRationale(fragment)
                }
                else -> {
                    Timber.i("handlePermissionRequest: else was called")
                    requestPermission()
                }
            }
        }

    }


    private fun allPermissionGranted(fragment: Fragment) =
        requiredPermissions.all { it.isGranted(fragment) }


    private fun shouldShowRationaleDialog(fragment: Fragment) =
        requiredPermissions.any { it.requiresRationale(fragment) }


    private fun sendPositiveResult() {
        Timber.i("sendPositiveResult() was called")
        sendResultAndCleanUp(getPermissionList().associateWith { true })
    }

    private fun showRationale(fragment: Fragment) {
        MaterialAlertDialogBuilder(fragment.requireContext())
            .setTitle(titleDialog)
            .setMessage(rationaleDialog)
            .setPositiveButton(fragment.getString(R.string.dialog_positive_button)) { dialog, _ ->
                dialog.dismiss()
                requestPermission()
            }
            .show()
    }

    private fun requestPermission() {
        Timber.i("requestPermission was called")
        permissionCheck?.launch(getPermissionList())
    }


    private fun getPermissionList() =
        requiredPermissions.flatMap { permission ->
            permission.permissions.toList()
        }.toTypedArray()


    private fun sendResultAndCleanUp(result: Map<String, Boolean>) {
        Timber.i("sendResultAndCleanUp() was called")
        callback(result.all { it.value })
        cleanUp()
    }

    private fun cleanUp() {
        requiredPermissions.clear()
        titleDialog = null
        rationaleDialog = null
        callback = {}
        detailedCallback = {}
    }

    private fun Permission.isGranted(fragment: Fragment) =
        permissions.all { hasPermissionGranted(fragment, it) }

    private fun Permission.requiresRationale(fragment: Fragment) =
        permissions.any { fragment.shouldShowRequestPermissionRationale(it) }

    private fun hasPermissionGranted(fragment: Fragment, permission: String) =
        ContextCompat.checkSelfPermission(
            fragment.requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
}