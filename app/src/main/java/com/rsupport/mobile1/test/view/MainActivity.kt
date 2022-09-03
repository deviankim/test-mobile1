package com.rsupport.mobile1.test.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.adapter.GettyImageAdapter
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.network.Uistate
import com.rsupport.mobile1.test.viewmodel.GettyImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        const val SPAN_COUNT = 3
    }

    private val viewModel: GettyImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.gettyImageViewModel = viewModel
        val gettyAdapter = GettyImageAdapter()
        binding.gettyImageAdapter = gettyAdapter
        viewModel.uiStateLiveData.observe(this) { uiState ->
            when (uiState) {
                is Uistate.Fail -> Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
                Uistate.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                is Uistate.Success ->
                    gettyAdapter.submitList(uiState.list)
            }
        }

        val gridManager = GridLayoutManager(this, SPAN_COUNT)
        binding.gettyImageList
            .apply {
                layoutManager = gridManager
                adapter = gettyAdapter
            }
        binding.gettyImageList.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!binding.gettyImageList.canScrollVertically(1))
                        viewModel.loadNextPage()
                }
            }
        )
    }
}
