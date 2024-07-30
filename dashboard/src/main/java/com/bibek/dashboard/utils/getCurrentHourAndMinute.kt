package com.bibek.dashboard.utils

import java.util.Calendar
fun getCurrentTimeIn12HourFormat(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
    }

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val period = if (hour < 12) "AM" else "PM"
    val hourIn12Format = when {
        hour == 0 -> 12  // Midnight case
        hour > 12 -> hour - 12
        else -> hour
    }
    val formattedMinutes = if (minute < 10) "0$minute" else minute.toString()

    return "$hourIn12Format:$formattedMinutes $period"
}

