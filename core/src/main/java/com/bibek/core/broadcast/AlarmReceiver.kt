package com.bibek.core.broadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.bibek.core.R
import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var recipeAlarmDao: RecipeAlarmDao

    override fun onReceive(context: Context, intent: Intent) {
        val recipeId = intent.getIntExtra("RECIPE_ID",0)
        val recipe = getRecipeById(recipeId)
        recipe?.let {
            showNotification(context, recipeId=it.id.toString(), recipeName =  it.title, recipeImage = it.image)
        }
    }
    private fun getRecipeById(id: Int?): RecipeAlarmEntity? {
        return id?.let {
            runBlocking {
                withContext(Dispatchers.IO) {
                    recipeAlarmDao.getRecipeAlarmById(it)
                }
            }
        }
    }
    private fun showNotification(context: Context, recipeId: String, recipeName: String, recipeImage: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "recipe_notify_channel"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Recipe Notifications", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Recipe Reminder")
            .setContentText("Don't forget to check out the recipe: $recipeName")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .build()
        notificationManager.notify(recipeId.hashCode(), notification)
    }
}
