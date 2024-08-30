package com.rsupport.mobile1.test.domain.use_case

import com.rsupport.mobile1.test.common.ErrorMessages
import com.rsupport.mobile1.test.common.Resource
import com.rsupport.mobile1.test.domain.repository.GettyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetImageUrlsUseCase @Inject constructor(private val gettyRepository: GettyRepository) {
    operator fun invoke(phrase: String): Flow<Resource<List<String>>> = flow {
        try{
            emit(Resource.Loading())
            val imageUrls = gettyRepository.getImageUrls(phrase = phrase)
            emit(Resource.Success(data = imageUrls))
        }
        catch (e: HttpException) {
            emit(Resource.Error(message = ErrorMessages.HTTP_EXCEPTION))
        }
        catch (e: IOException) {
            emit(Resource.Error(message = ErrorMessages.IO_EXCEPTION))
        }
        catch (e: Exception) {
            emit(Resource.Error(message = ErrorMessages.UNKNOWN_EXCEPTION))
        }
    }
}