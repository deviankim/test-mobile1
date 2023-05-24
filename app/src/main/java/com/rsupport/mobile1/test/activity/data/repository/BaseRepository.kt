package com.rsupport.mobile1.test.activity.data.repository

import android.util.Log
import com.rsupport.mobile1.test.activity.api.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Log.e("TEST","server throwable : ${throwable.toString()}")
                when (throwable) {
                    is HttpException -> {
                        //TODO : 서버 에러
                        Resource.Failure(
                            throwable.code(),
                            null
                        )
                    }
                    is UnknownHostException -> {
                        //TODO : 네트워크 에러
                        Resource.Failure(null, null)
                    }
                    is CancellationException -> {
                        Resource.Failure(null, null)
                    }
                    else -> {
                        //TODO : 기타 에러
                        Resource.Failure(null, null)
                    }
                }
            }
        }
    }
}