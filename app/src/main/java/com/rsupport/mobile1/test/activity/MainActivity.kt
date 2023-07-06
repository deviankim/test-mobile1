package com.rsupport.mobile1.test.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.util.BaseActivity
import com.rsupport.mobile1.test.util.ScrollListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            viewmodel = viewModel
            adapter = ImageAdapter()
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            recyclerview.addOnScrollListener(ScrollListener() {
                viewModel.fetchNextImageList()
            })
        }

        viewModel.imageFlow.observe(this) {
            Timber.d("list : $it")
            binding.adapter?.addImageList(it)
        }

        viewModel.isLoading.observe(this) {isLoading ->
            if (isLoading)
                binding.loadingIndicator.visibility = View.VISIBLE
            else
                binding.loadingIndicator.visibility = View.INVISIBLE
        }
    }
}