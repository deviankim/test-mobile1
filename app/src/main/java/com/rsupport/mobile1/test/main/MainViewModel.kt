package com.rsupport.mobile1.test.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.model.dto.Asset
import com.rsupport.mobile1.test.model.ui.GalleryModel
import com.rsupport.mobile1.test.network.GettyImagesService
import com.rsupport.mobile1.test.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gettyImagesService: GettyImagesService
) : ViewModel() {

    private val _galleryModelFlow = MutableStateFlow<List<GalleryModel>>(emptyList())
    val galleryModelFlow = _galleryModelFlow.asStateFlow()

    init {
        LogUtil.d("init MainViewModel")
    }

    fun getPhotosCollaboration() {
        LogUtil.d("request get photos collaboration")
        viewModelScope.launch {
            val gettyImages = gettyImagesService.getPhotosCollaborationAsync().await()
            gettyImages.gallery.assets.map { asset ->
                asset.toGalleyModel()
            }.run {
                _galleryModelFlow.emit(this)
            }
        }
    }

    private fun Asset.toGalleyModel(): GalleryModel {
        return GalleryModel(artist, collectionName, family, id, shortTitle, licenseType, thumbUrl)
    }

}