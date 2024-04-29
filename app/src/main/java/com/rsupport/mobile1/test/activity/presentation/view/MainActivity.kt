package com.rsupport.mobile1.test.activity.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.lifecycleScope
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.presentation.base.BindingActivity
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.reflect.ParameterizedType

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var adapter: GettyAdapter
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
        stateObserver()
    }
    fun setUpUi(){
        binding.vm = this@MainActivity.viewModel
    }
    fun stateObserver(){
        lifecycleScope.launch {
            viewModel.getImage.collect(){
                if(it){
                    adapter = GettyAdapter(){

                    }
                    binding.rvCalendar.adapter = adapter
                    adapter.submitList(viewModel.gettyList.value!!)
                }
            }
        }
    }
}