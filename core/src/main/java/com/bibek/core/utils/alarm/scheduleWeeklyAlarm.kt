package com.bibek.core.utils.alarm
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.bibek.core.broadcast.AlarmReceiver
import java.util.Calendar

fun scheduleWeeklyAlarm(
    context: Context,
    alarmId: Int,
    dayOfWeek: Int,
    hour: Int,
    minute: Int,
    recipeId: String,
    recipeName: String,
    recipeImage: String
) {
    val alarmManager : AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java).apply {
        putExtra("RECIPE_ID", recipeId)
        putExtra("RECIPE_NAME", recipeName)
        putExtra("RECIPE_IMAGE", recipeImage)
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT
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
    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        AlarmManager.INTERVAL_DAY * 7,
        pendingIntent
    )
}
