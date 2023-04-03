package com.rsupport.mobile1.test.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.FragmentGettyImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GettyImageFragment : Fragment() {

    private lateinit var binding: FragmentGettyImageBinding

    private val gettyImageViewModel: GettyImageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGettyImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserving()
        loadData()
    }

    private fun initView() {
        binding.noticeTextView.apply {
            visibility = View.VISIBLE
            text = getString(R.string.loading_data)
        }

        binding.gettyImageRecyclerView.apply {
            visibility = View.GONE
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = GettyImageAdapter()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserving() {
        gettyImageViewModel.loadImageList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("jenny", "observing, loading")
                    binding.gettyImageRecyclerView.visibility = View.GONE
                    binding.noticeTextView.visibility = View.VISIBLE
                    binding.noticeTextView.text = getString(R.string.loading_data)
                }

                is Resource.Success -> {
                    binding.gettyImageRecyclerView.visibility = View.VISIBLE
                    binding.noticeTextView.visibility = View.GONE
                    (binding.gettyImageRecyclerView.adapter as GettyImageAdapter).let {
                        Log.d("jenny", "observing, success result.data.toList().size: ${result.data.toList().size}")
                        it.submitList(result.data.toList())
                    }
                }

                is Resource.Failure -> {
                    Log.d("jenny", "observing, failure: ${result.e}")
                    binding.gettyImageRecyclerView.visibility = View.GONE
                    binding.noticeTextView.visibility = View.VISIBLE
                    binding.noticeTextView.text = getString(R.string.failed_load_data)
                }

                else -> {
                    // nothing to do..
                }
            }
        }
    }

    private fun loadData() {
        gettyImageViewModel.loadGettyImageList()
    }

    companion object {
        @JvmStatic
        fun newInstance() = GettyImageFragment()
    }
}