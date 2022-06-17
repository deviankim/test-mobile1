package com.rsupport.mobile1.test.activity.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.activity.data.GettyImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GettyImageViewModel @Inject constructor(
    private val repository: GettyImageRepository
) : ViewModel() {

    private val _showProgress = MutableLiveData(true)
    val showProgress: LiveData<Boolean> = _showProgress

    val imageUrlBankFlow: Flow<List<String>> = repository.gettySearchResultStream
        .distinctUntilChanged()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), 1)
        .onStart {
            _showProgress.postValue(true)
            repository.searchImage(DEFAULT_QUERY)
            _showProgress.postValue(false)
        }

    companion object {
        private const val DEFAULT_QUERY = "collaboration"
    }
}