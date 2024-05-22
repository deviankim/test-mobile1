package com.kimwijin.domain.model

/**
 * 이미지 정보를 가지는 도메인 모델 클래스 입니다.
 * 이 클래스는 비즈니스 요구사항을 구현하고, 다양한 서비스 계층과 데이터 계층 사이에서 정보를 전달하는 역할을 합니다.
 *
 * @author (김위진)
 * @since (2024-05-22)
 */
data class ImageInfo(
    val src: String,
    val title: String,
    val width: String,
    val height: String
)