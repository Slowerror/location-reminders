package com.slowerror.locationreminders.data.repository

import com.slowerror.locationreminders.domain.model.Reminder
import com.slowerror.locationreminders.domain.repository.ReminderRepository

class ReminderRepositoryImpl: ReminderRepository {

    override fun getReminders(): List<Reminder> {
        TODO("Not yet implemented")
    }

    override fun getReminderById(reminderId: Long): Reminder {
        TODO("Not yet implemented")
    }

    override fun saveReminder(reminder: Reminder) {
        TODO("Not yet implemented")
    }

    override fun removeAllReminders() {
        TODO("Not yet implemented")
    }

    override fun removeReminder(reminder: Reminder) {
        TODO("Not yet implemented")
    }
}