package com.rsupport.mobile1.test.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.adapter.ImageListAdapter
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.server.resp.ImageData
import com.rsupport.mobile1.test.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mViewModel
        binding.lifecycleOwner = this

        setImageListAdapter()
        mViewModel.getImageList()
    }

    fun setImageListAdapter() {
        binding.recyclerImageList.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        var imageListAdapter: ImageListAdapter = ImageListAdapter()
        imageListAdapter.setOnItemClickListener(object : ImageListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: ImageData) {
                var url = item.downloads?.url
            }
        })
        binding.recyclerImageList.adapter = imageListAdapter
    }
}