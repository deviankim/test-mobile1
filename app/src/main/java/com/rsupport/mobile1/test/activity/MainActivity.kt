package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.data.RemoteGettyImage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RemoteGettyImage.getInstance().getGettyImage(1);
    }
}