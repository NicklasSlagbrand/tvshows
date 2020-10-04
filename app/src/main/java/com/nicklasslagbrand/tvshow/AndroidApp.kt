package com.nicklasslagbrand.tvshow

import android.app.Application
import com.nicklasslagbrand.tvshow.core.di.androidPlatformModule
import com.nicklasslagbrand.tvshow.core.di.generalAppModule
import com.nicklasslagbrand.tvshow.core.di.useCaseModule
import com.nicklasslagbrand.tvshow.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@Suppress("unused")
class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.ERROR)
            androidContext(this@AndroidApp)

            modules(
                listOf(
                    androidPlatformModule,
                    generalAppModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
