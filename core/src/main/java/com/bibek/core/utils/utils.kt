package com.bibek.core.utils

fun formatTo12Hour(hours: Int, minutes: Int): String {
    val period = if (hours < 12) AM else PM
    val hourIn12Format = when {
        hours == 0 -> 12  // Midnight case
        hours > 12 -> hours - 12
        else -> hours
    }
    val formattedMinutes = if (minutes < 10) "0$minutes" else minutes.toString()
    return "$hourIn12Format:$formattedMinutes $period"
}