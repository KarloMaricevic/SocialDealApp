package com.karlomaricevic.socialdeal

import android.app.Application
import timber.log.Timber

class SocialDealApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
