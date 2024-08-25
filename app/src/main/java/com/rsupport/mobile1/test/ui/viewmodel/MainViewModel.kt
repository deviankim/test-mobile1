package com.rsupport.mobile1.test.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.data.model.ImageData
import com.rsupport.mobile1.test.data.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Lee on 2024-08-23.
 * ViewModel for the MainActivity to manage UI-related data and state.
 */
class MainViewModel : ViewModel() {
    private val repository = ImageRepository()

    private val _images = MutableLiveData<List<ImageData>>()
    val images: LiveData<List<ImageData>> get() = _images

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> get() = _error

    init {
        loadImages() // Load images when the ViewModel is created
    }

    // Function to load images asynchronously
    private fun loadImages() {
        viewModelScope.launch {
            _loading.value = true // Show loading indicator
            try {
                val imageList = withContext(Dispatchers.IO) { repository.fetchImages() }
                _images.value = imageList // Update UI with the image list
            } catch (e: Exception) {
                _error.value = e.message // Handle error
            } finally {
                _loading.value = false // Hide loading indicator
            }
        }
    }

    // Function to clear error messages
    fun clearError() {
        _error.value = null
    }
}