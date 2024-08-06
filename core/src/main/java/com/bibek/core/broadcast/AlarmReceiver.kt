package com.bibek.core.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.di.AlarmReceiverEntryPoint
import com.bibek.core.utils.ALARM_ID
import com.bibek.core.utils.alarm.scheduleWeeklyAlarm
import com.bibek.core.utils.notification.showCustomNotification
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlarmReceiver : BroadcastReceiver() {
    private lateinit var recipeAlarmDao: RecipeAlarmDao
    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(context, AlarmReceiverEntryPoint::class.java)
        recipeAlarmDao = hiltEntryPoint.recipeAlarmDao()
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        GlobalScope.launch(exceptionHandler + Dispatchers.IO) {
            val alarmId = intent.getIntExtra(ALARM_ID, 0)
            val recipeAlarmEntity = recipeAlarmDao.getRecipeAlarmById(alarmId)
            recipeAlarmEntity?.apply {
                if (isRepeat) {
                    withContext(Dispatchers.Main){
                        scheduleWeeklyAlarm(
                            context = context,
                            alarmId = alarmId,
                            dayOfWeek = dayOfWeek ?: return@withContext,
                            hour = hour ?: return@withContext,
                            minute = minute ?: return@withContext,
                            isRepeat = true,
                            recipeId = recipeId,
                        )
                    }
                }
                // Create and show the notification
                recipeAlarmEntity.image?.let {
                    withContext(Dispatchers.Main) {
                        showCustomNotification(
                            context,
                            title = "Time to eat",
                            recipeId = recipeId,
                            recipeName = name,
                            image = it
                        )
                    }
                }
            }
        }
    }
}

