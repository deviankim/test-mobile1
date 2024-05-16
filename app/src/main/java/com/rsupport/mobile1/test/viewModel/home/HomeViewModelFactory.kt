package com.rsupport.mobile1.test.viewModel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rsupport.mobile1.test.service.GettyService

class HomeViewModelFactory(private val api: GettyService): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(api) as T
    }

}