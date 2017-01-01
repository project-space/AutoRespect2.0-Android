package ru.annin.autorespect

import android.app.Application
import android.support.v7.app.AppCompatDelegate

/**
 * Класс приложения.
 *
 * @author Pavel Annin.
 */
class AutoRespectApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}