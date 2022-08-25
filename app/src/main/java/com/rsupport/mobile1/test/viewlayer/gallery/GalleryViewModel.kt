package com.rsupport.mobile1.test.viewlayer.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.model.dto.Asset
import com.rsupport.mobile1.test.model.ui.GalleryItem
import com.rsupport.mobile1.test.model.ui.ThumbnailInfo
import com.rsupport.mobile1.test.repository.GettyImagesRepository
import com.rsupport.mobile1.test.repository.ResourceRepository
import com.rsupport.mobile1.test.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val gettyImagesRepository: GettyImagesRepository,
) : ViewModel() {

    companion object {
        const val SPAN_COUNT = 3
    }

    private val _galleryUiStateFlow = MutableStateFlow<GalleryUiState>(GalleryUiState.Loading)
    val galleryUiStateFlow = _galleryUiStateFlow.asStateFlow()

    init {
        LogUtil.d("init MainViewModel")
    }

    fun getPhotosCollaboration() {
        viewModelScope.launch {
            _galleryUiStateFlow.emit(GalleryUiState.Loading)
            LogUtil.d("request photos collaboration")
            try {
                val gettyImages = gettyImagesRepository.getPhotosCollaboration()
                gettyImages.gallery.assets.toGalleryUiStateSuccess().run {
                    _galleryUiStateFlow.emit(this)
                }
            } catch (e: Exception) {
                LogUtil.e(e)
                _galleryUiStateFlow.emit(GalleryUiState.Failure)
            }
        }
    }

    private fun List<Asset>.toGalleryUiStateSuccess(): GalleryUiState.Success {
        return GalleryUiState.Success(this.map { it.toGalleyItem() })
    }

    private fun Asset.toGalleyItem(): GalleryItem {
        val family = family.replaceFirstChar { it.uppercaseChar() }
        val thumbnailSize = resourceRepository.getDimensionResource(R.dimen.thumbnail_override_size)
        return GalleryItem(artist, collectionName, family,
            id, shortTitle, licenseType, ThumbnailInfo(thumbUrl, thumbnailSize))
    }

}