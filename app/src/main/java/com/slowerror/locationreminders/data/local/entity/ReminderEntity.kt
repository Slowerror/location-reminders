package com.slowerror.locationreminders.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String,
    @ColumnInfo(name = "title_location") val titleLocation: String,
    val latitude: Double,
    val longitude: Double,
    @ColumnInfo(name = "is_enabled") val isEnabled: Boolean
)