package com.example.proyecto3corte.Database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTypeConverter {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(dateFormatter)
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return try {
            dateString?.let { LocalDate.parse(it, dateFormatter) }
        } catch (e: Exception) {
            null // Manejo de errores para cadenas no válidas
        }
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.format(dateTimeFormatter)
    }

    @TypeConverter
    fun toLocalDateTime(dateTimeString: String?): LocalDateTime? {
        return try {
            dateTimeString?.let { LocalDateTime.parse(it, dateTimeFormatter) }
        } catch (e: Exception) {
            null // Manejo de errores para cadenas no válidas
        }
    }
}

