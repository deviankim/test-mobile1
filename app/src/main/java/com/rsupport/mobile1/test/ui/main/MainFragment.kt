package com.rsupport.mobile1.test.ui.main

import androidx.fragment.app.viewModels
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.comm.BaseFragment
import com.rsupport.mobile1.test.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModels()
    private var page = 1

    override fun initView() {
        val adapter = PhotoListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.crawlFromWeb(page)
        subscribeUi(adapter)
        setPage()
    }

    private fun subscribeUi(adapter: PhotoListAdapter) {
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setPage() {
        binding.imgBack.setOnClickListener {
            if (page <= 1)
                return@setOnClickListener
            page--
            viewModel.crawlFromWeb(page)
        }

        binding.imgForward.setOnClickListener {
            page++
            viewModel.crawlFromWeb(page)
        }
    }
}