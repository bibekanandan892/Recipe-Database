package com.bibek.core.utils

import android.content.Intent
import java.util.Calendar

const val RECIPE_ID = "RECIPE_ID"
const val IS_REPEAT = "IS_REPEAT"
const val HOUR = "HOUR"
const val MINUTE = "MINUTE"
const val DAY_OF_WEEK = "DAY_OF_WEEK"
const val ALARM_ID = "ALARM_ID"
const val CHANNEL_ID = "recipe_reminder_channel"
const val CHANNEL_NAME = "Recipe Notifications"
const val CHANNEL_DESCRIPTION = "Channel for recipe notifications"
const val DEEP_LINK_BASE_URI = "app://com.bibek.recipedatabase/"
const val SOUND_URI = "android.resource://"
const val FLAG_ACTIVITY = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
const val NOTIFICATION_ID_DEFAULT = 0
const val CONNECTION_TIMEOUT_MESSAGE = "Connection Timeout"
const val SOCKET_TIMEOUT_MESSAGE = "Socket Timeout"
const val UNKNOWN_IO_ERROR_MESSAGE = "Unknown IO Error"
const val GENERIC_ERROR_MESSAGE = "Something went wrong"
const val UNKNOWN_ERROR_MESSAGE = "Unknown Error"
const val ERROR_DESCRIPTION_KEY = "error_description"
const val MESSAGE_KEY = "message"
const val STATUS_DESC_KEY = "statusDesc"
const val PAGE_SIZE = 20
const val AM = "AM"
const val PM = "PM"
const val LOG_TAG = "Ktor Client"
const val DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS"
const val NAME_PREFIX = "Name : "
const val DETAILS_PREFIX = "Details : "
const val DEFAULT_NAME = "NA"
const val DEFAULT_IMAGE = "NA"

const val TIME_TO_EAT_MESSAGE = "Time to eat"


enum class Day(val dayOfWeek: Int,val dayName : String) {
    SUN(dayOfWeek = Calendar.SUNDAY, dayName = "Sunday"),
    MON(dayOfWeek = Calendar.MONDAY, dayName = "Monday"),
    TUE(dayOfWeek = Calendar.TUESDAY, dayName = "Tuesday"),
    WED(dayOfWeek = Calendar.WEDNESDAY, dayName = "Wednesday"),
    THU(dayOfWeek = Calendar.THURSDAY, dayName = "Thursday"),
    FRI(dayOfWeek = Calendar.FRIDAY, dayName = "Friday"),
    SAT(dayOfWeek = Calendar.SATURDAY, dayName = "Saturday")
}


