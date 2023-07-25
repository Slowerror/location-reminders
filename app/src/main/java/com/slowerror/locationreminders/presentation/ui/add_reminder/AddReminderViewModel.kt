package com.slowerror.locationreminders.presentation.ui.add_reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddReminderViewModel : ViewModel() {

    private val _radius: MutableLiveData<Float> = MutableLiveData()
    val radius: LiveData<Float> = _radius

    fun setRadius(value: Float) {
        _radius.value = value
    }
}