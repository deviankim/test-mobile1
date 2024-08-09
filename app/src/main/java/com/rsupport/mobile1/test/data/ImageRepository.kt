package com.rsupport.mobile1.test.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.rsupport.mobile1.test.model.GettyItem
import okhttp3.logging.HttpLoggingInterceptor

class ImageRepository {
    fun getImageSearch(): Flow<PagingData<GettyItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = ImageDataSource.defaultDisplay,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ImageDataSource()
            }
        ).flow
    }
}