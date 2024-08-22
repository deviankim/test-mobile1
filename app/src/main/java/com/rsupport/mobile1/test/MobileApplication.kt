package com.rsupport.mobile1.test

import android.app.Application
import com.rsupport.mobile1.test.image.ImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ImageLoader.init(applicationContext)
    }
}