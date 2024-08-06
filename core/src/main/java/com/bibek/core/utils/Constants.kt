package com.bibek.core.utils

import java.util.Calendar

const val RECIPE_ID = "RECIPE_ID"
const val IS_REPEAT = "IS_REPEAT"
const val HOUR = "HOUR"
const val MINUTE = "MINUTE"
const val DAY_OF_WEEK = "DAY_OF_WEEK"
const val ALARM_ID = "ALARM_ID"

enum class Day(val dayOfWeek: Int,val dayName : String) {
    SUN(dayOfWeek = Calendar.SUNDAY, dayName = "Sunday"),
    MON(dayOfWeek = Calendar.MONDAY, dayName = "Monday"),
    TUE(dayOfWeek = Calendar.TUESDAY, dayName = "Tuesday"),
    WED(dayOfWeek = Calendar.WEDNESDAY, dayName = "Wednesday"),
    THU(dayOfWeek = Calendar.THURSDAY, dayName = "Thursday"),
    FRI(dayOfWeek = Calendar.FRIDAY, dayName = "Friday"),
    SAT(dayOfWeek = Calendar.SATURDAY, dayName = "Saturday")
}


