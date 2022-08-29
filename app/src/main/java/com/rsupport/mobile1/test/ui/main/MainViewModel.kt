package com.rsupport.mobile1.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.data.model.Photo
import com.rsupport.mobile1.test.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    fun crawlFromWeb(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _photos.postValue(repository.crawlFromWeb(page))
        }
    }
}