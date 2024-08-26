package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.entity.GettyImage
import com.example.domain.repository.GettyImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGettyImageUseCase @Inject constructor(
    private val gettyImageRepository: GettyImageRepository
) {

    operator fun invoke(): Flow<PagingData<GettyImage>> = gettyImageRepository.getGettyImages()
}