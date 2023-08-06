package com.slowerror.locationreminders.data.mapper

interface Mapper <E, D> {
    fun mapToData(type: E): D

    fun mapToDomain(type: D): E
}