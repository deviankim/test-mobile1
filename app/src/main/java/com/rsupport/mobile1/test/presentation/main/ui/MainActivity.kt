package com.rsupport.mobile1.test.presentation.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.presentation.main.ui.list.MainListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainListAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initTodoListRecyclerView()
    }

    private fun initTodoListRecyclerView() {
        mainListAdapter = MainListAdapter()
        binding.rvMainList.adapter = mainListAdapter
    }
}