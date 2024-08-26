package com.bibek.core.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.di.AlarmReceiverEntryPoint
import com.bibek.core.utils.alarm.scheduleWeeklyAlarm
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BootBroadcastReceiver : BroadcastReceiver() {
    private lateinit var recipeAlarmDao: RecipeAlarmDao

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            if (context == null) return

            val hiltEntryPoint =
                EntryPointAccessors.fromApplication(context, AlarmReceiverEntryPoint::class.java)

            recipeAlarmDao = hiltEntryPoint.recipeAlarmDao()

            val exceptionHandler = hiltEntryPoint.coroutineExceptionHandler()
            val dispatchers = hiltEntryPoint.coroutinesDispatchers()

            GlobalScope.launch(exceptionHandler + dispatchers.io()) {
                recipeAlarmDao.getAllRecipeAlarm().first().forEach { recipeAlarmEntity ->
                    recipeAlarmEntity.apply {
                        if (isRepeat) {
                            withContext(dispatchers.main()) {
                                scheduleWeeklyAlarm(
                                    context = context,
                                    alarmId = id ?: return@withContext,
                                    dayOfWeek = dayOfWeek ?: return@withContext,
                                    hour = hour ?: return@withContext,
                                    minute = minute ?: return@withContext,
                                    isRepeat = true,
                                    recipeId = recipeId,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
