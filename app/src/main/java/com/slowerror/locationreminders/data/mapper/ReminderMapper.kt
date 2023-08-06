package com.slowerror.locationreminders.data.mapper

import com.slowerror.locationreminders.data.local.entity.ReminderEntity
import com.slowerror.locationreminders.domain.model.Reminder

class ReminderMapper : Mapper<Reminder, ReminderEntity> {
    override fun mapToData(type: Reminder): ReminderEntity {
        return ReminderEntity(
            id = type.id,
            title = type.title,
            description = type.description,
            titleLocation = type.titleLocation,
            latitude = type.latitude,
            longitude = type.longitude,
            isEnabled = type.isEnabled
        )
    }

    override fun mapToDomain(type: ReminderEntity): Reminder {
        return Reminder(
            id = type.id,
            title = type.title,
            description = type.description,
            titleLocation = type.titleLocation,
            latitude = type.latitude,
            longitude = type.longitude,
            isEnabled = type.isEnabled
        )
    }
}