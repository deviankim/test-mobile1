package com.rsupport.mobile1.test

import androidx.multidex.MultiDexApplication
import com.rsupport.mobile1.test.module.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MobileApplication : MultiDexApplication() {

    lateinit var serviceUrl: String

    override fun onCreate() {
        super.onCreate()

        serviceUrl = getString(R.string.service_url)
        startKoin {
            androidContext(this@MobileApplication)
            modules(apiModule)
        }
    }
}