package com.rsupport.mobile1.test.di

import com.rsupport.mobile1.test.network.baseurl.BaseUrlFactory
import com.rsupport.mobile1.test.network.baseurl.TestBaseUrlFactoryImpl
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class TestNetworkModule : NetworkModule() {

    override fun createBaseUrlFactory(): BaseUrlFactory {
        return TestBaseUrlFactoryImpl()
    }

}