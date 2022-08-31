package com.rsupport.mobile1.test.data.repository

import com.rsupport.mobile1.test.data.GettyImage
import kotlinx.coroutines.flow.flow
import org.jsoup.select.Elements
import retrofit2.HttpException
import java.util.concurrent.Flow

class GettyImageRepository {
    suspend operator fun invoke(element: Elements):
            kotlinx.coroutines.flow.Flow<GettyImage> {
        return flow {
            try {
            } catch (httpException: HttpException) {

            }
        }
    }
}