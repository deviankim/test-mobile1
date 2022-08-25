package com.rsupport.mobile1.test.activity.Presentation.View

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler

import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.rsupport.mobile1.test.activity.Presentation.BaseFile.BaseActivity
import com.rsupport.mobile1.test.activity.Presentation.State.LoadingState
import com.rsupport.mobile1.test.activity.Presentation.ViewModel.LoadingViewModel
import com.rsupport.mobile1.test.databinding.ActivityLoadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingActivity : BaseActivity<LoadingViewModel>() {
    private lateinit var binding: ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun observeData() {
        viewModel.ImageLiveData.observe(this){ Loading ->
            when(Loading){
                is LoadingState.UnInitialized -> initData()
                is LoadingState.Loading -> handleLoading()
                is LoadingState.Success -> handleSuccess(Loading)
            }
        }
    }

    private fun handleSuccess(loading: LoadingState.Success) {

        loading.Loaded.forEach { Image ->
            viewModel.saveImageDataToLocalDB(Image)
        }
        binding.progress.isVisible = false
        binding.state.text = "Loading Complete!!"
        Handler().postDelayed({
            val intent = Intent(this,ImageListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
        },1200)
    }

    private fun handleLoading() = with(binding){
        state.text = "Loading!!"
        progress.isVisible = true
    }

    private fun initData() = with(binding){
        progress.isVisible = false
    }


    override val viewModel: LoadingViewModel by viewModels()

}