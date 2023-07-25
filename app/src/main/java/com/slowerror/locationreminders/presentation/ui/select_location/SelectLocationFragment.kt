package com.slowerror.locationreminders.presentation.ui.select_location

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import com.slowerror.locationreminders.R
import com.slowerror.locationreminders.databinding.FragmentSelectLocationBinding
import com.slowerror.locationreminders.presentation.ui.add_reminder.AddReminderViewModel
import kotlinx.coroutines.launch

class SelectLocationFragment : Fragment() {

    private var _binding: FragmentSelectLocationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddReminderViewModel by navGraphViewModels(R.id.addReminderNavGraph)

    private var _googleMap: GoogleMap? = null
    private val googleMap get() = _googleMap!!

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

        viewModel.radius.observe(viewLifecycleOwner) {
            Snackbar.make(view, "radius: $it", Snackbar.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                _googleMap = mapFragment.awaitMap()
                googleMap.awaitMapLoad()

                mapSettings(googleMap)
            }
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