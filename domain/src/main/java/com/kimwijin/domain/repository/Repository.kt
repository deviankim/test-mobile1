package com.kimwijin.domain.repository

import com.kimwijin.domain.base.Result
import com.kimwijin.domain.model.ImageInfo
import kotlinx.coroutines.flow.Flow

/**
 * Image 데이터를 조회하기 위한 레포지토리 인터페이스 입니다.
 * 이 인터페이스는 애플리케이션의 데이터 액세스 계층을 추상화하며, 데이터 소스로부터 데이터를 조회하는 역할을 정의합니다.
 *
 * Repository는 데이터의 일관된 관리를 보장하고, 비즈니스 로직과 데이터 액세스 로직을 분리하는데 중요한 역할을 합니다.
 * 이를 통해 어플리케이션의 유지보수성을 향상시키고, 데이터 액세스 방법의 변경에 따른 영향을 최소화 할 수 있습니다.
 *
 * 구현 클래스에서는 이 인터페이스를 사용하여 REST API 데이터 소스의 상세 구현을 제공 합니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
interface Repository {
    suspend fun getImages(page: String): Flow<Result<List<ImageInfo>>>
}