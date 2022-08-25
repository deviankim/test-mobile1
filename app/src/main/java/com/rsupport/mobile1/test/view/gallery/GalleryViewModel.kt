package com.rsupport.mobile1.test.view.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.model.dto.Asset
import com.rsupport.mobile1.test.model.ui.GalleryItem
import com.rsupport.mobile1.test.model.ui.GalleryUiModel
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

    private val _galleryUiModelFlow = MutableStateFlow(GalleryUiModel())
    val galleryUiModelFlow = _galleryUiModelFlow.asStateFlow()

    init {
        LogUtil.d("init MainViewModel")
    }

    fun getPhotosCollaboration() {
        viewModelScope.launch {
            LogUtil.d("request photos collaboration")
            val gettyImages = gettyImagesRepository.getPhotosCollaboration()
            gettyImages.gallery.assets.toGalleryUiModel().run {
                _galleryUiModelFlow.emit(this)
            }
        }
    }

    private fun List<Asset>.toGalleryUiModel(): GalleryUiModel {
        return GalleryUiModel(this.map { it.toGalleyItem() })
    }

    private fun Asset.toGalleyItem(): GalleryItem {
        val family = family.replaceFirstChar { it.uppercaseChar() }
        val thumbnailSize = resourceRepository.getDimensionResource(R.dimen.thumbnail_override_size)
        return GalleryItem(artist, collectionName, family,
            id, shortTitle, licenseType, ThumbnailInfo(thumbUrl, thumbnailSize))
    }

}
