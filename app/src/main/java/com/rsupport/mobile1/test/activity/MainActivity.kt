package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "GETTY_IMAGE"
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getImage()
    }
}