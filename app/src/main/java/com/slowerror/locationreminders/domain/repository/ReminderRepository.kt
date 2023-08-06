package com.slowerror.locationreminders.domain.repository

import com.slowerror.locationreminders.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    fun getReminders(): Flow<List<Reminder>>

    fun getReminderById(reminderId: Long): Flow<Reminder>

    fun saveReminder(reminder: Reminder)

    fun removeAllReminders()

    fun removeReminder(reminder: Reminder)
}