package com.rsupport.mobile1.test.activity.feature.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.activity.data.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ImageUiState(
  val imageUrlList: List<String> = emptyList()
)

@HiltViewModel
class ImageViewModel @Inject constructor(
  private val imageRepository: ImageRepository,
) : ViewModel() {
  private val page = MutableStateFlow(1)

  private val imageUrlList = page.map { page ->
    val result = imageRepository.getImageUrlList(
      page = page,
    ).first()

    uiState.value.imageUrlList + result
  }

  val uiState: StateFlow<ImageUiState> = combine(
    imageUrlList
  ) { (imageUrlList) ->

    ImageUiState(
      imageUrlList = imageUrlList
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000L),
    initialValue = ImageUiState()
  )

  fun getNextPage() {
    page.update { it + 1 }
  }
}
