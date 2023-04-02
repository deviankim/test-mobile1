package com.rsupport.mobile1.test.activity

import android.app.Application

class GettyImageApplication : Application() {

    init {
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: GettyImageApplication
    }
}