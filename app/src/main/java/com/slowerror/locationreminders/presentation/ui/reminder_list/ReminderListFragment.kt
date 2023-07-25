package com.slowerror.locationreminders.presentation.ui.reminder_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.slowerror.locationreminders.R
import com.slowerror.locationreminders.databinding.FragmentReminderListBinding
import com.slowerror.locationreminders.presentation.ui.reminder_list.adapter.ReminderAdapter

class ReminderListFragment : Fragment() {

    private var _binding: FragmentReminderListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReminderListViewModel by viewModels()
    private lateinit var reminderAdapter: ReminderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        viewModel.reminderList.observe(viewLifecycleOwner) {
            reminderAdapter.submitList(it)
        }

        navigateToAddReminder()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        reminderAdapter = ReminderAdapter { id -> navigateToReminderDetail(id) }

        with(binding.reminderRw) {
            adapter = reminderAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun navigateToReminderDetail(id: Long) {
        findNavController().navigate(R.id.action_reminderListFragment_to_reminderDetailFragment)
    }

    private fun navigateToAddReminder() {
        binding.addReminderFab.setOnClickListener {
            findNavController().navigate(R.id.action_reminderListFragment_to_addReminderFragment)
        }
    }
}