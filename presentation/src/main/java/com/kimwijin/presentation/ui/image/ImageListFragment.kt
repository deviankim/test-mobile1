package com.kimwijin.presentation.ui.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimwijin.domain.model.ImageInfo
import com.kimwijin.presentation.R
import com.kimwijin.presentation.base.BaseFragment
import com.kimwijin.presentation.databinding.FragmentImageListBinding
import com.kimwijin.presentation.ui.image.adapter.ImageListAdpater
import com.kimwijin.presentation.util.ext.gone
import com.kimwijin.presentation.util.ext.text
import com.kimwijin.presentation.util.ext.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * 이미지 리스트 화면을 표시하기 위한 Fragment 클래스입니다.
 * 이 클래스는 기본적으로 BaseFragment를 상속받고, FragmentListBinding 이용하여 레이아웃을 바인딩합니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
@AndroidEntryPoint
class ImageListFragment : BaseFragment<FragmentImageListBinding>(R.layout.fragment_image_list) {

    private val viewModel: ImageListViewModel by viewModels()

    private lateinit var imageListAdapter: ImageListAdpater

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageListBinding {
        return FragmentImageListBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        initListView()
        initRetryView()
        observeViewState()
    }

    private fun initListView() {
        imageListAdapter = ImageListAdpater()
        binding.rvImages.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = imageListAdapter
        }

        binding.rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val fab = binding.btFab
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val currentPosition = layoutManager.findFirstVisibleItemPosition()

                if (currentPosition < 10) {
                    fab.hide()
                    return
                }

                if (dy > 10 && fab.isShown) {
                    fab.hide()
                }

                if (dy < -10 && !fab.isShown) {
                    fab.show()
                }
            }
        })

        binding.btFab.hide()
        binding.btFab.setOnClickListener {
            if (imageListAdapter.itemCount > 10 ) {
                binding.rvImages.scrollToPosition(0)
            } else {
                binding.rvImages.smoothScrollToPosition(0)
            }
        }
    }

    private fun initRetryView() {
        binding.btRetry.setOnClickListener {
            loadData()
        }
    }

    private fun observeViewState() {
        viewModel.state.onEach { state ->
            when (state) {
                ImageListViewState.Empty -> showError(requireContext().text(R.string.result_empty_text))
                is ImageListViewState.Loading -> showLoading()
                is ImageListViewState.GetImages -> showData(state.imageInfos)
                is ImageListViewState.Error -> showError(state.message)
            }
        }.launchIn(lifecycleScope)
    }

    private fun showLoading() {
        binding.apply {
            progress.show()
            rvImages.gone()
            tvMessage.gone()
            btRetry.gone()
        }
    }

    private fun showData(imageInfos: List<ImageInfo>) {
        binding.apply {
            progress.hide()
            rvImages.visible()
            tvMessage.gone()
            btRetry.gone()
        }
        imageListAdapter.submitList(imageInfos)
    }

    private fun showError(message: String?) {
        binding.apply {
            progress.hide()
            rvImages.gone()
            tvMessage.visible()
            btRetry.visible()
            tvMessage.text = message
        }
    }

    override fun loadData() {
        viewModel.getImages()
    }
}