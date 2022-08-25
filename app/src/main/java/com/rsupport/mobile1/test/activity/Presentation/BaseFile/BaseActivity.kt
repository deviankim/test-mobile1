package com.rsupport.mobile1.test.activity.Presentation.BaseFile

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job

abstract class BaseActivity<vm : BaseViewModel> : AppCompatActivity() {
    abstract fun observeData()
    private lateinit var fetchJob : Job
    abstract val viewModel : vm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchJob = viewModel.fetchData()
        observeData()
    }

    override fun onDestroy() {
        if(fetchJob.isActive){
            fetchJob.cancel()
        }
        super.onDestroy()
    }
}