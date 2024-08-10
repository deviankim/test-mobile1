package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.activity.adapter.ImageAdapter
import com.rsupport.mobile1.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = ImageAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.recyclerImageList.layoutManager = gridLayoutManager
        binding.recyclerImageList.adapter = adapter

        onButtonClick() // 버튼 클릭 리스너
    }

    private fun onButtonClick() {
        binding.buttonSearch.setOnClickListener {}
    }
}