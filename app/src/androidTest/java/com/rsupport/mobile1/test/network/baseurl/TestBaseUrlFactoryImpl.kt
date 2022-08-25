package com.rsupport.mobile1.test.network.baseurl

class TestBaseUrlFactoryImpl : BaseUrlFactory {
    override fun getGettyImages(): String {
        return "http://127.0.0.1:8080/"
    }
}