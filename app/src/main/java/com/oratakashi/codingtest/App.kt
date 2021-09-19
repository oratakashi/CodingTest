package com.oratakashi.codingtest

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.oratakashi.codingtest.di.libModule
import com.oratakashi.codingtest.di.reqresModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
        )
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                libModule,
                reqresModule
            ))
            androidLogger(Level.INFO)
        }
    }
}