package com.rsupport.mobile1.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.databinding.FragmentImageBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImageFragment : Fragment() {

    private lateinit var imageViewModel: ImageViewModel
    private val adapter: ImageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageViewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImageBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            imageViewModel.pagingDataFlow
                .collectLatest { items ->
                    adapter.submitData(items)
                }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        imageViewModel.handleQuery()

        return binding.root
    }
}