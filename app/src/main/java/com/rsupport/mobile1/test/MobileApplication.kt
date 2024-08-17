package com.rsupport.mobile1.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}