package com.rsupport.mobile1.test.activity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rsupport.mobile1.test.databinding.FragmentGettyImageListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class GettyImageListFragment : Fragment() {
    private var _binding: FragmentGettyImageListBinding? = null
    private val binding get() = _binding!!

    private val adapter = GettyImageListAdapter()

    private val viewModel: GettyImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGettyImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        binding.thumbnailList.adapter = adapter
    }

    private fun initData() {
        lifecycleScope.launchWhenResumed {
            viewModel.imageUrlBankFlow.collect(adapter::submitList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}