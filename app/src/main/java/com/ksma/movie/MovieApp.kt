package com.ksma.movie

import android.app.Application
import com.ksma.movie.di.*
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(
            this, listOf(
                appModule, interactorModule, mapperModule, presenterModule,
                repositoryModule, networkModule, cacheModule, internalStorageModule
            )
        )
    }
}