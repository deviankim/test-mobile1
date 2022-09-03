package com.rsupport.mobile1.test.data.repository

import com.rsupport.mobile1.test.data.DataSource
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.data.RemoteGettyImage
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class GettyImageRepository @Inject constructor(private val remoteGettyImage: RemoteGettyImage): DataSource{
    override suspend fun getGettyImage(page: Int): MutableList<GettyImage> {
        return remoteGettyImage.getGettyImage(page)
    }

}