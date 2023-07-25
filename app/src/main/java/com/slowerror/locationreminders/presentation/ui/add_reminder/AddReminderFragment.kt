package com.slowerror.locationreminders.presentation.ui.add_reminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.slowerror.locationreminders.R
import com.slowerror.locationreminders.databinding.FragmentAddReminderBinding

class AddReminderFragment : Fragment() {

    private var _binding: FragmentAddReminderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddReminderViewModel by navGraphViewModels(R.id.addReminderNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.radius.observe(viewLifecycleOwner) {
            binding.radiusSlider.value = it
        }

        binding.selectLocationButton.setOnClickListener {
            viewModel.setRadius(binding.radiusSlider.value)

            findNavController().navigate(R.id.action_addReminderFragment_to_selectLocationFragment)
        }



        binding.saveFab.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}