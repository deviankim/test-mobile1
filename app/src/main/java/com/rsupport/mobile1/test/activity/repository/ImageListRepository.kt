package com.rsupport.mobile1.test.activity.repository

import com.rsupport.mobile1.test.activity.data.Images
import io.reactivex.Single

/**
 * 이미지 데이터 파싱 Repository 인터페이스
 */
interface ImageListRepository {
    fun requestImageElements(): Single<ArrayList<Images>>
}