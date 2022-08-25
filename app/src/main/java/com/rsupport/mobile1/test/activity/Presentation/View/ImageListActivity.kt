package com.rsupport.mobile1.test.activity.Presentation.View

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.Data.Repository.ImageRepository
import com.rsupport.mobile1.test.activity.Presentation.Adapter.ImageAdapter
import com.rsupport.mobile1.test.activity.Presentation.BaseFile.BaseActivity
import com.rsupport.mobile1.test.activity.Presentation.State.ImageState
import com.rsupport.mobile1.test.activity.Presentation.ViewModel.ImageListViewModel
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import org.jsoup.Jsoup
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class ImageListActivity : BaseActivity<ImageListViewModel>() {
    private val adapter = ImageAdapter()
    private lateinit var binding: ActivityMainBinding
    override fun observeData() {
        viewModel.ImageLiveData.observe(this){ Image ->
            when(Image){
                is ImageState.UnInitialized -> initViews()
                is ImageState.Loading -> handleLoading()
                is ImageState.Success -> handleSuccess(Image)
                is ImageState.Error -> handleError()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun handleSuccess(image: ImageState.Success) = with(binding){
        swipe.isRefreshing = false
        adapter.setData(image.Images)
    }

    private fun handleError() {
    }



    private fun handleLoading() = with(binding){
        swipe.isRefreshing = true
    }

    private fun initViews() = with(binding){
        imageRecycler.adapter = adapter
        imageRecycler.layoutManager = GridLayoutManager(this@ImageListActivity,3)

        swipe.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    override val viewModel: ImageListViewModel by viewModels()


}