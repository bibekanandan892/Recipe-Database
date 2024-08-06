package com.bibek.dashboard.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bibek.dashboard.R
import java.util.Calendar

class NotifyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val recipeId = inputData.getString("RECIPE_ID")
        val recipeName = inputData.getString("RECIPE_NAME")
        val dayOfWeek = inputData.getInt("DAY_OF_WEEK", -1)
        val hour = inputData.getInt("HOUR", -1)
        val minute = inputData.getInt("MINUTE", -1)

        val calendar = Calendar.getInstance()
        if (calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek &&
            calendar.get(Calendar.HOUR_OF_DAY) == hour &&
            calendar.get(Calendar.MINUTE) == minute) {
            showNotification(recipeId, recipeName)
        }
        return Result.success()
    }

    private fun showNotification(recipeId: String?, recipeName: String?) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "recipe_notify_channel"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Recipe Notifications", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Recipe Reminder")
            .setContentText("Don't forget to check out the recipe: $recipeName")
            .setSmallIcon(R.drawable.logo_recipe) // Add your notification icon here
            .build()
        notificationManager.notify(recipeId?.hashCode() ?: 0, notification)
    }
}
