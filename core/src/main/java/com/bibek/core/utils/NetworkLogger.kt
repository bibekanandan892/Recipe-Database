package com.bibek.core.utils

import android.util.Log
import com.bibek.core.BuildConfig
import io.ktor.client.plugins.logging.Logger
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NetworkLogger : Logger {
    private val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault())

    override fun log(message: String) {
        if (BuildConfig.DEBUG) {
            val timestamp = dateFormat.format(Date())
            Log.d(LOG_TAG, "[$timestamp] $message")
        }
    }
}