package com.slowerror.locationreminders.domain.repository

import com.slowerror.locationreminders.domain.model.Reminder

interface ReminderRepository {

    fun getReminders(): List<Reminder>

    fun getReminderById(reminderId: Long): Reminder

    fun saveReminder(reminder: Reminder)

    fun removeAllReminders()

    fun removeReminder(reminder: Reminder)
}