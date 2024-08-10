package com.bibek.core.utils.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.bibek.core.R
import com.bibek.core.utils.CHANNEL_DESCRIPTION
import com.bibek.core.utils.CHANNEL_ID
import com.bibek.core.utils.CHANNEL_NAME
import com.bibek.core.utils.DEEP_LINK_BASE_URI
import com.bibek.core.utils.FLAG_ACTIVITY
import com.bibek.core.utils.NOTIFICATION_ID_DEFAULT
import com.bibek.core.utils.SOUND_URI
import com.bibek.core.utils.navigation.Destination

fun showCustomNotification(
    context: Context,
    recipeId: String,
    title: String,
    recipeName: String,
    image: Bitmap
) {

    // Create an intent for the action you want to perform when the notification is clicked
    val deepLinkUri = Uri.parse("$DEEP_LINK_BASE_URI${Destination.RECIPE_ALARM_DETAILS.name}/$recipeId")

    val intent = Intent(Intent.ACTION_VIEW, deepLinkUri).apply {
        flags = FLAG_ACTIVITY
    }

    // Create a PendingIntent using the intent
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val soundUri = Uri.parse("$SOUND_URI${context.packageName}/raw/notification")

    // Create the notification channel (Android 8.0 and above)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
        val channel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = CHANNEL_DESCRIPTION
            setSound(soundUri, attributes)
        }
        notificationManager.createNotificationChannel(channel)
    }

    val smallIcon = getAppIconBitmap(context)
    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.food)
        .setLargeIcon(image)
        .setStyle(NotificationCompat.BigPictureStyle().bigPicture(image).bigLargeIcon(smallIcon))
        .setContentTitle(title)
        .setContentText(recipeName)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setSound(soundUri)
        .build()
    notificationManager.notify(
        try {
            recipeId.toInt()
        } catch (e: Exception) {
            NOTIFICATION_ID_DEFAULT
        }, notification
    )
}

fun getAppIconBitmap(context: Context): Bitmap {
    // Retrieve the application icon drawable
    val appIcon: Drawable = context.packageManager.getApplicationIcon(context.applicationInfo)
    // Convert the drawable to a bitmap
    val bitmap = Bitmap.createBitmap(
        appIcon.intrinsicWidth,
        appIcon.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // Create a canvas and draw the drawable onto it
    val canvas = Canvas(bitmap)
    appIcon.setBounds(0, 0, canvas.width, canvas.height)
    appIcon.draw(canvas)
    return bitmap
}
