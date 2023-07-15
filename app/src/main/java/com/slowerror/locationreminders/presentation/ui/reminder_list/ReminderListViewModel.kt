package com.slowerror.locationreminders.presentation.ui.reminder_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slowerror.locationreminders.domain.model.Reminder

class ReminderListViewModel : ViewModel() {

    private val reminder = Reminder(
        title = "Title Reminder 1",
        description = "Description Reminder",
        titleLocation = "Title Location 1",
        latitude = 0.0,
        longitude = 0.0,
        isEnabled = false
    )

    private val listReminder = mutableListOf<Reminder>()
    private val _reminderList: MutableLiveData<List<Reminder>> = MutableLiveData<List<Reminder>>()
    val reminderList: LiveData<List<Reminder>> = _reminderList

    init {
        for (i in 0..12) {
            listReminder.add(i, reminder)
        }

        _reminderList.value = listReminder.toList()
    }



}