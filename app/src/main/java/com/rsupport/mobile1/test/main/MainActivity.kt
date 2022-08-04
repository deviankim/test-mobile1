package com.rsupport.mobile1.test.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setLayout()
        observerData()
    }

    private fun setLayout() {
        binding.listView.setTag(R.string.view_tag,R.layout.getty_item)
        viewModel.gettingImage()
    }

    private fun observerData(){
       viewModel.loadingLiveData.observe(this){
           if(it) binding.progressBar.show() else binding.progressBar.hide()
       }

       viewModel.gettyLiveData.observe(this){
            binding.model = it
       }
    }
}