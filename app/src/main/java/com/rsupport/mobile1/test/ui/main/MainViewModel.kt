package com.rsupport.mobile1.test.ui.main

import androidx.lifecycle.ViewModel
import com.rsupport.mobile1.test.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
}