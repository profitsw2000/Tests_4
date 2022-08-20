package com.geekbrains.tests

import android.app.Application
import android.content.Context
import com.geekbrains.tests.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }