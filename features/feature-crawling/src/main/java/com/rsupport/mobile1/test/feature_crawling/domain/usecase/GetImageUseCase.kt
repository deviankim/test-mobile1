package com.rsupport.mobile1.test.feature_crawling.domain.usecase

import com.rsupport.mobile1.test.core.repository.ImageRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(page: Int): List<String> = imageRepository.getImage(page)
}