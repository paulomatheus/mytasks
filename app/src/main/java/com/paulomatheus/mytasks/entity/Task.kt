package com.paulomatheus.mytasks.entity

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

data class  Task(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val date: LocalDate? = null,
    val completed: Boolean = false,
    val time: LocalTime? = null

) : Serializable {
    fun formatDate(): String {
        return date?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
    }

    fun formatTime(): String {
        return time?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: ""
    }

    fun formatDateTime(): String {
        return if (date != null && time != null) {
            "${formatDate()} ${formatTime()}"
        } else if (date != null) {
            formatDate()
        } else if (time != null) {
            formatTime()
        } else {
            "-"
        }
    }
}
