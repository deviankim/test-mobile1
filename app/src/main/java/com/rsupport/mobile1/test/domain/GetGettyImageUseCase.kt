package com.rsupport.mobile1.test.domain

import androidx.lifecycle.LiveData
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.data.repository.GettyImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGettyImageUseCase @Inject constructor(private val gettyImageRepository: GettyImageRepository){

    suspend fun getGettyImages(page: Int): MutableList<GettyImage> {
      return  gettyImageRepository.getGettyImage(page)
    }
}