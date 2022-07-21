package com.rsupport.mobile1.test.activity.di

import com.rsupport.mobile1.test.activity.data.GettyImageDataSource
import com.rsupport.mobile1.test.activity.data.GettyImageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindGettyImageDataSource(gettyImageDataSource: GettyImageDataSourceImpl): GettyImageDataSource
}