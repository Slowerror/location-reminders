package com.slowerror.locationreminders.presentation.ui.reminder_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slowerror.locationreminders.domain.model.Reminder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

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


    private fun aba() {
        loadFlow()
            .filter { it > 2 }
            .onEach {

            }
            .launchIn(viewModelScope)



    }

    private fun loadFlow() = flow {
        for (i in 0..5) {
            emit(i)
        }
    }

}