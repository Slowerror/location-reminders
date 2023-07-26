package com.slowerror.locationreminders.presentation.ui.select_location

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.snackbar.Snackbar
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import com.slowerror.locationreminders.R
import com.slowerror.locationreminders.databinding.FragmentSelectLocationBinding
import com.slowerror.locationreminders.presentation.ui.add_reminder.AddReminderViewModel
import com.slowerror.locationreminders.presentation.ui.utils.permission.Permission
import com.slowerror.locationreminders.presentation.ui.utils.permission.PermissionManager
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectLocationFragment : Fragment() {

    private var _binding: FragmentSelectLocationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddReminderViewModel by navGraphViewModels(R.id.addReminderNavGraph)

    private var _googleMap: GoogleMap? = null
    private val googleMap get() = _googleMap!!

    private val permissionManager = PermissionManager.from(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                _googleMap = mapFragment.awaitMap()
                googleMap.awaitMapLoad()

                mapSettings(googleMap)
            }
        }

        binding.userLocationFab.setOnClickListener {
            permissionManager
                .request(Permission.Location)
                .titleAndRationale(
                    this.getString(R.string.rationale_dialog_title_user_location),
                    this.getString(R.string.rationale_dialog_description_user_location)
                )
                .checkPermission { isGranted ->
                    Timber.i("checkPermission: isGranted = $isGranted")
                    if (isGranted) {
                        Snackbar.make(view, "Разрешение получено", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(view, "Разрешение отклонено", Snackbar.LENGTH_SHORT).show()
                    }
                }

        }

    }

    private fun getAppSettingsIntent(): Intent {
        return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            data = Uri.parse("package:" + (context?.packageName ?: "ghbnjmk"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _googleMap = null
    }

    private fun mapSettings(map: GoogleMap) {
        with(map.uiSettings) {
            isMapToolbarEnabled = false
            isMyLocationButtonEnabled = false
            isCompassEnabled = false
        }

    }

}