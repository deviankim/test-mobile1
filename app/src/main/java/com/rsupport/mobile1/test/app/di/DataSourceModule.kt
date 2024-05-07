package com.rsupport.mobile1.test.app.di

import com.rsupport.mobile1.test.data.source.main.MainDataSource
import com.rsupport.mobile1.test.data.source.main.MainDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsMainDataSource(mainDataSourceImpl: MainDataSourceImpl): MainDataSource
}