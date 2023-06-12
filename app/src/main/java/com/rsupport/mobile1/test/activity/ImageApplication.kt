package com.rsupport.mobile1.test.activity

import android.app.Application
import com.rsupport.mobile1.test.activity.repository.repositoryModule
import com.rsupport.mobile1.test.activity.repository.viewmodel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ImageApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * Koin 사용하여 모듈화처리
         */
        startKoin {
            androidContext(this@ImageApplication)
            modules(viewmodel, repositoryModule)
        }
    }
}