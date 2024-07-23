package com.bibek.core.utils

import android.util.Log
import com.bibek.core.BuildConfig
import io.ktor.client.plugins.logging.Logger
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NetworkLogger : Logger {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    override fun log(message: String) {
        if (BuildConfig.DEBUG) {
            val timestamp = dateFormat.format(Date())
            Log.d("Ktor Client", "[$timestamp] $message")
        }
    }
}