package com.rsupport.mobile1.test.server

object ApiConst {
    internal val BASE_URL = "https://api.gettyimages.com"

    //API KEY 를 개인이 발급받는 방법을 조사해 보았는데 찾지 못했습니다.
    //API KEY 를 발급받았다는 가정 하에 Retrofit 으로 구현합니다.
    internal const val API_KEY = "api-key: <API_KEY>"

    internal const val CONTENT_TYPE_SIGN_IN = "Content-Type: application/x-www-form-urlencoded"
    internal const val ACCEPT_SEARCH_IMAGES = "accept: application/json"

    private const val API_VER_3 = "/v3"
    private const val API_VER_4 = "/v4"

    internal const val API_SIGN_IN = "$API_VER_4/oauth2/token"
    internal const val API_SEARCH_IMAGES = "$API_VER_3/search/images"

    internal const val API_TIMEOUT: Long = 30
}