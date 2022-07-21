package com.rsupport.mobile1.test.activity.presenter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.data.GettyImageWebImpl
import com.rsupport.mobile1.test.activity.domain.GettyImageRepositoryImpl
import com.rsupport.mobile1.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "GETTY_IMAGE"
    }

    private val viewModel: MainViewModel by lazy {
        val repository = GettyImageRepositoryImpl(GettyImageWebImpl())
        val factory = MainViewModel.Factory(repository)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.gettyImageList.observe(this) {
            Log.d(TAG, it.toString())
        }

        viewModel.requestImage()
    }
}