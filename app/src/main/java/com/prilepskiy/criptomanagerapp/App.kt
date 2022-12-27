package com.prilepskiy.criptomanagerapp

import android.app.Application
import com.prilepskiy.criptomanagerapp.di.*
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidContext(applicationContext)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(modules)

        }

    }

    private val modules = listOf(
        repositoryModule,
        useCaseModule,
        viewModelModule,
        databaseModule,
        apiModule
        )

}