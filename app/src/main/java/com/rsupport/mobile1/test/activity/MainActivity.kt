package com.rsupport.mobile1.test.activity

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.adapter.ImageAdapter
import com.rsupport.mobile1.test.base.BaseActivity
import com.rsupport.mobile1.test.common.ScrollListener
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    private var currentPage = 1
    private var lastTimeBackPressed: Long = 0
    private var isBack = false

    override fun initView() {
        with(binding) {
            adapter = ImageAdapter()
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            recyclerview.addOnScrollListener(ScrollListener {
                currentPage++
                viewModel.fetchImageList(currentPage)
            })
        }

        viewModel.imageLiveData.observe(this) {
            binding.adapter?.addImageList(it)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading)
                binding.loadingIndicator.visibility = View.VISIBLE
            else
                binding.loadingIndicator.visibility = View.INVISIBLE
        }
        viewModel.isError.observe(this) { isError ->
            if (isError) {
                binding.errorLayout.visibility = View.VISIBLE
                binding.recyclerview.visibility = View.INVISIBLE
            } else {
                binding.errorLayout.visibility = View.INVISIBLE
                binding.recyclerview.visibility = View.VISIBLE
            }
        }
    }

    override fun initDataBinding() {
        binding.vm = viewModel
    }

    override fun onEvent() {
        // 데이터 오류 발생시 재시도 버튼 클릭 이벤트
        binding.btnRetry.setOnClickListener {
            viewModel.fetchImageList(currentPage)
        }
    }

    override fun onBackPressed() {
        isBack = true

        if (System.currentTimeMillis() - lastTimeBackPressed < 2000) {
            finish()
            return
        }
        lastTimeBackPressed = System.currentTimeMillis()
        Toast.makeText(this, getString(R.string.main_back_finished), Toast.LENGTH_SHORT).show()
    }
}