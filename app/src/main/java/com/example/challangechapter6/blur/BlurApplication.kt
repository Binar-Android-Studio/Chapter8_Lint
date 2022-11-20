package com.example.challangechapter6.blur

import android.app.Application
import android.util.Log
import androidx.viewbinding.BuildConfig
import androidx.work.Configuration
import timber.log.Timber

class BlurApplication : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration {
        return if (BuildConfig.DEBUG) {
            Configuration.Builder()
                .setMinimumLoggingLevel(Log.DEBUG)
                .build()
        } else {
            Configuration.Builder()
                .setMinimumLoggingLevel(Log.ERROR)
                .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
