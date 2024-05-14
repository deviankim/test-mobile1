package com.blue.data.remote.datasource

import android.util.Log
import retrofit2.Retrofit
import javax.inject.Inject

class PhotoDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): PhotoDataSource {
    override suspend fun getPhotoDataSource() {
        val response = retrofit.create(PhotoDataSource::class.java).getPhotoDataSource()
        Log.e("TAG", "$response", )
    }
}