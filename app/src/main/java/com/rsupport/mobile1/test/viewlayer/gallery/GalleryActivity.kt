package com.rsupport.mobile1.test.viewlayer.gallery

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import com.rsupport.mobile1.test.util.LogUtil
import com.rsupport.mobile1.test.viewlayer.decoration.GalleryDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private val viewModel: GalleryViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private fun ActivityMainBinding.showUi(mask: Int) {
        circularPb.isVisible = GalleryUiState.MASK_LOADING and mask > 0
        errorTv.isVisible = GalleryUiState.MASK_FAILURE and mask > 0
        galleryRv.isVisible = GalleryUiState.MASK_SUCCESS and mask > 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.d("")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.galleryRv.run {
                setHasFixedSize(true)
                addItemDecoration(GalleryDecoration(
                    GalleryViewModel.SPAN_COUNT,
                    resources.getDimension(R.dimen.gallery_space).toInt()
                ))
                layoutManager = GridLayoutManager(this@GalleryActivity, GalleryViewModel.SPAN_COUNT)
                adapter = MainAdapter()
            }
            setContentView(it.root)
        }

        lifecycleScope.launch {
            viewModel.galleryUiStateFlow.collectLatest { uiState ->
                binding.showUi(uiState.mask)
                if (uiState is GalleryUiState.Success) {
                    (binding.galleryRv.adapter as MainAdapter).submitList(uiState.items)
                }
            }
        }
    }

    override fun onStart() {
        LogUtil.d("")
        super.onStart()
        viewModel.getPhotosCollaboration()
    }
}
