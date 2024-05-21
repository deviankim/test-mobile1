package com.kimwijin.domain.base

/**
 * 연산 결과를 캡슐화하는 sealed interface입니다.
 * 이 interface는 연산이 성공한 경우와 실패 또는 Exception 이 발생한 경우를 명확히 구분하여 표현할 수 있도록 도와줍니다.
 * T는 연산에서 반환되어야 할 데이터의 타입을 나타냅니다.
 *
 * Result는 다음 두 가지 상태 중 하나를 가질 수 있습니다:
 * - Success: 연산이 성공적으로 완료되었을 때, 연관된 데이터를 포함합니다.
 * - Failure: 연산 중 오류가 발생했을 때, 오류 정보를 포함합니다.
 * - Exception: 연산 중 예외가 발생했을 때, 예외 정보를 포함합니다.
 *
 * 이 패턴을 사용하면 함수의 반환 타입을 통해 오류를 명시적으로 처리할 수 있으며,
 * 함수의 사용자는 성공과 오류를 쉽게 구분하여 각각에 대한 처리를 할 수 있습니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure<T>(val code: String?, val message: String?) : Result<T>
    data class Exception(val throwable: Throwable? = null) : Result<Nothing>
}