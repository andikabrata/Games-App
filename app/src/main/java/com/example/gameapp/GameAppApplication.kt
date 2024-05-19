package com.example.gameapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.gameapp.core.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import timber.log.Timber

/**
 * @author Andika Bratadirja
 * @date 16/05/2024
 */
class GameAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@GameAppApplication)
            modules(provideComponent())
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    /**
     * provide list of modules @see [appComponent]
     */
    private fun provideComponent(): List<Module> {
        return appComponent
    }

    companion object {
        lateinit var instance: GameAppApplication
            private set
    }
}