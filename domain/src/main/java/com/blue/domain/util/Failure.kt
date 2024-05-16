package com.blue.domain.util

sealed class Failure(val message: String){
    data object BadRequest: Failure(Constants.ERROR_NOT_FOUND)
    data object NetworkConnection: Failure(Constants.ERROR_INTERNET_CONNECTED)
    class UnHandleError(message: String = Constants.ERROR_UNHANDLED): Failure(message)
}
