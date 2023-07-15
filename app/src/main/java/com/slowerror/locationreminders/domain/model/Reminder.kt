package com.slowerror.locationreminders.domain.model

data class Reminder(
    val id: Long = 0,
    val title: String,
    val description: String,
    val titleLocation: String,
    val latitude: Double,
    val longitude: Double,
    val isEnabled: Boolean
)