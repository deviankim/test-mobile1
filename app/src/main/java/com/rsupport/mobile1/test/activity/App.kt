package com.rsupport.mobile1.test.activity

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.bumptech.glide.Glide
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleListener())
    }

    override fun onTerminate() {
        super.onTerminate()
        ProcessLifecycleOwner.get().lifecycle.removeObserver(AppLifecycleListener())
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory();
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }

    class AppLifecycleListener : DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) { // app moved to foreground
        }

        override fun onStop(owner: LifecycleOwner) { // app moved to background
        }
    }
}