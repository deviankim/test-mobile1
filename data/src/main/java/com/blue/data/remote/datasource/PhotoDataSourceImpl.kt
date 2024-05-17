package com.blue.data.remote.datasource

import com.blue.data.mapper.Mapper.asResponse
import com.blue.data.remote.model.ServerResponse
import com.blue.data.remote.state.ResponseState
import com.blue.data.remote.util.errorHandler
import com.blue.data.remote.util.jsonParser
import retrofit2.Retrofit
import javax.inject.Inject

class PhotoDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getPhotoDataSource(): ResponseState<List<ServerResponse>> {
        val response = retrofit.create(PhotoDataSource::class.java).getPhotoDataSource()
        return when(val responseState = response.errorHandler()){
            is ResponseState.Success -> ResponseState.Success(data = responseState.data.jsonParser().asResponse())
            is ResponseState.Error -> ResponseState.Error(responseState.failure)
        }
    }
}

