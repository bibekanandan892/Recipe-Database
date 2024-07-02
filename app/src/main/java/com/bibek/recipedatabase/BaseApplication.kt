package com.bibek.recipedatabase

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltTestApplication;

@HiltAndroidApp
class BaseApplication:Application()

// A custom runner to set up the instrumented application class for tests.
class AppTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
