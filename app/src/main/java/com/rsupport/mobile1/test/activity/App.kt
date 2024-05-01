package com.rsupport.mobile1.test.activity

import android.app.Application
import coil.ImageLoader
import com.rsupport.mobile1.test.activity.ui.util.CoilImageLoader

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        imageLoader = CoilImageLoader().getImageLoader(this)
    }

    companion object {
        lateinit var imageLoader: ImageLoader
    }
}