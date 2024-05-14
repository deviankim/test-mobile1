package com.rsupport.mobile1.test.core.mapper

import com.rsupport.mobile1.test.core.model.ImageUiModel
import com.rsupport.mobile1.test.network_api.entity.Image

object UiModelMapper {
    fun Image.mapToUiModel() =
        ImageUiModel(
            url = this.url
        )
}