package com.bibek.dashboard.utils

import android.content.Context
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.bibek.dashboard.work.NotifyWorker
import java.util.concurrent.TimeUnit

fun scheduleRecipeNotification(
    context: Context,
    recipeId: String,
    recipeName: String,
    recipeImage: String,
    dayOfWeek: Int,
    hour: Int,
    minute: Int
) {
    val data = Data.Builder()
        .putString("RECIPE_ID", recipeId)
        .putString("RECIPE_NAME", recipeName)
        .putString("RECIPE_IMAGE", recipeImage)
        .putInt("DAY_OF_WEEK", dayOfWeek)
        .putInt("HOUR", hour)
        .putInt("MINUTE", minute)
        .build()

    val workRequest = PeriodicWorkRequest.Builder(NotifyWorker::class.java, 7, TimeUnit.DAYS)
        .setInputData(data)
        .addTag(recipeId)  // Use the recipe ID as a tag
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}
