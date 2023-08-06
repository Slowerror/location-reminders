package com.slowerror.locationreminders.data.repository

import com.slowerror.locationreminders.data.local.ReminderDatabase
import com.slowerror.locationreminders.data.local.dao.ReminderDao
import com.slowerror.locationreminders.data.local.entity.ReminderEntity
import com.slowerror.locationreminders.data.mapper.ReminderMapper
import com.slowerror.locationreminders.domain.model.Reminder
import com.slowerror.locationreminders.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map

class ReminderRepositoryImpl(
    private val reminderDao: ReminderDao,
    private val reminderMapper: ReminderMapper
) : ReminderRepository {

    override fun getReminders(): Flow<List<Reminder>> =
        reminderDao.getAllReminders()
            .map { list ->
                list.map { reminderMapper.mapToDomain(it) }
            }


    override fun getReminderById(reminderId: Long): Flow<Reminder> =
        reminderDao.getReminderById(reminderId)
            .map { reminderEntity ->
                reminderMapper.mapToDomain(reminderEntity)
            }

    override fun saveReminder(reminder: Reminder) {
        val reminderEntity = reminderMapper.mapToData(reminder)
        reminderDao.saveReminder(reminderEntity)
    }

    override fun removeAllReminders() {
        reminderDao.removeReminders()
    }

    override fun removeReminder(reminder: Reminder) {
        val reminderEntity = reminderMapper.mapToData(reminder)
       reminderDao.removeReminder(reminderEntity)
    }
}