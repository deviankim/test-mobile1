package com.rsupport.mobile1.test.util

import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse


fun <T> T.toMockResponse(): MockResponse {
    return MockResponse().setBody(Gson().toJson(this).toString())
}
