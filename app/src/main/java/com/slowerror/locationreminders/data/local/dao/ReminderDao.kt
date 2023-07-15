package com.slowerror.locationreminders.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slowerror.locationreminders.data.local.entity.ReminderEntity

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminders")
    fun getAllReminders(): List<ReminderEntity>

    @Query("SELECT * FROM reminders WHERE id = :reminderId")
    fun getReminderById(reminderId: Long): ReminderEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReminder(reminder: ReminderEntity)

    @Delete
    fun removeReminder(reminder: ReminderEntity)

    @Query("DELETE FROM reminders")
    fun removeReminders()
}