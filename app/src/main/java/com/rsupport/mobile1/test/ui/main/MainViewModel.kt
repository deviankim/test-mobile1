package com.rsupport.mobile1.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rsupport.mobile1.test.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {

    private val _text = MutableLiveData<String>("Hello RSUPPORT")
    val text: LiveData<String> get() = _text
}