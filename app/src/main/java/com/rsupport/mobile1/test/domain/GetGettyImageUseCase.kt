package com.rsupport.mobile1.test.domain

import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.data.repository.GettyImageRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetGettyImageUseCase @Inject constructor(private val gettyImageRepository: GettyImageRepository) {

    suspend fun getGettyImages(page: Int): MutableList<GettyImage> {
        return gettyImageRepository.getGettyImage(page)
    }
}