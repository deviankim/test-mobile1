package com.rsupport.mobile1.test.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rsupport.mobile1.test.adapter.GettyImageAdapter
import com.rsupport.mobile1.test.adapter.ItemSpacingDecoration
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val gettyImageAdapter by lazy {
        GettyImageAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        observeUiData()
    }

    private fun initRecyclerView() {
        with(binding.rvGetty) {
            adapter = gettyImageAdapter
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL
            ).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
            layoutManager = staggeredGridLayoutManager
            addItemDecoration(ItemSpacingDecoration(4))
        }
    }

    private fun observeUiData() {
        lifecycleScope.launch {
            mainViewModel.gettyImages.collectLatest { pagingData ->
                gettyImageAdapter.submitData(pagingData)
            }
        }
    }
}
