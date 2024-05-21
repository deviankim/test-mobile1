package com.kimwijin.presentation.ui.image

import com.kimwijin.domain.model.ImageInfo

/**
 * 메인화면의 상태를 표현하는 Sealed 클래스 입니다.
 * 이 클래스는 메인화면이 가질 수 있는 다양한 상태를 정의하고,
 * 각 상태에 따라 화면의 UI를 다르게 처리할 수 있도록 합니다.
 * Sealed 클래스로 구현 되어 있어 상태를 나타내는 하위 클래스를 지정할 수 있습니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
sealed class ImageListViewState {
    data object Empty : ImageListViewState()
    data object Loading : ImageListViewState()
    data class GetImages(val imageInfos: List<ImageInfo>) : ImageListViewState()
    data class Error(val message: String?) : ImageListViewState()
}