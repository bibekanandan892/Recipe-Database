package com.bibek.core.utils.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.bibek.core.broadcast.AlarmReceiver
import com.bibek.core.utils.ALARM_ID
import com.bibek.core.utils.DAY_OF_WEEK
import com.bibek.core.utils.HOUR
import com.bibek.core.utils.IS_REPEAT
import com.bibek.core.utils.MINUTE
import com.bibek.core.utils.RECIPE_ID
import java.util.Calendar

fun scheduleWeeklyAlarm(
    context: Context,
    alarmId: Int,
    dayOfWeek: Int,
    hour: Int,
    minute: Int,
    isRepeat : Boolean,
    recipeId: String,
) {
    val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java).apply {
        putExtra(RECIPE_ID, recipeId)
        putExtra(IS_REPEAT, isRepeat)
        putExtra(HOUR, hour)
        putExtra(MINUTE, minute)
        putExtra(DAY_OF_WEEK, dayOfWeek)
        putExtra(ALARM_ID, alarmId)
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context, alarmId, intent, PendingIntent.FLAG_IMMUTABLE
    )
    val calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, dayOfWeek)
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        if (before(Calendar.getInstance())) {
            add(Calendar.WEEK_OF_YEAR, 1)
        }
    }
    try {
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    } catch (e: SecurityException) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!checkExactAlarmPermission(context)) {
                requestExactAlarmPermission(context)
            }
        }
    }
}
