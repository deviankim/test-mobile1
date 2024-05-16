package com.rsupport.mobile1.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.FragmentHomeBinding
import com.rsupport.mobile1.test.service.GettyService
import com.rsupport.mobile1.test.viewModel.home.HomeViewModel
import com.rsupport.mobile1.test.viewModel.home.HomeViewModelFactory
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {
    private lateinit var homeViewModelFactory: HomeViewModelFactory

    private lateinit var homeViewModel: HomeViewModel

    private val api: GettyService by inject()

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModelFactory = HomeViewModelFactory(api)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        homeViewModel.loadData(binding.listContainer)

        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this
    }
}