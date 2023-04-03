package com.rsupport.mobile1.test.activity

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Mobile1TestApp : Application() {

    init {
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: Mobile1TestApp
    }
}