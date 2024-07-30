package com.bibek.core.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.utils.alarm.scheduleWeeklyAlarm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var recipeAlarmDao: RecipeAlarmDao
    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            GlobalScope.launch {
                val alarms = recipeAlarmDao.getAllRecipeAlarm()
                alarms.forEach {alarm->
                    scheduleWeeklyAlarm(
                        context = context, alarmId =  alarm.id?:0,
                        dayOfWeek = alarm.dayOfWeek?:0,
                        hour = alarm.hour?:0,
                        minute = alarm.min?:0,
                        recipeId = alarm.id.toString(),
                        recipeName = alarm.title,
                        recipeImage = alarm.image
                    )
                }
            }
        }
    }
}

