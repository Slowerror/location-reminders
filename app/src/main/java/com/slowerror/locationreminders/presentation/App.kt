package com.slowerror.locationreminders.presentation

import android.app.Application
import timber.log.Timber
import com.slowerror.locationreminders.BuildConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}