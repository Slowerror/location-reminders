package com.slowerror.locationreminders.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.slowerror.locationreminders.data.local.dao.ReminderDao
import com.slowerror.locationreminders.data.local.entity.ReminderEntity

@Database(entities = [ReminderEntity::class], version = 1, exportSchema = false)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        fun getInstance(context: Context): ReminderDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        ReminderDatabase::class.java,
                        "locationReminders.db"
                    ).fallbackToDestructiveMigration().build()
                }

                return instance
            }
        }
    }
}