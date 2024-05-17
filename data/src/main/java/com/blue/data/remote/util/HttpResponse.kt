package com.blue.data.remote.util

import android.util.Log
import com.blue.data.remote.state.ResponseState
import com.blue.domain.util.Constants
import com.blue.domain.util.Failure
import retrofit2.Response
import java.io.IOException

/**
 * 네트워크 통신 중 발생하는 예외상황을 처리하는 함수입니다.
 *
 * @param Retrofit으로부터 Response<T> 객체를 전달받습니다.
 *
 * @return 예외 상황을 처리 후, ResponseState<T>으로 반환합니다.
 */
inline fun <reified T: Any> Response<T>.errorHandler(): ResponseState<T> {
    return try{
        when(this.code()){
            Constants.CODE_BAD_REQUEST -> ResponseState.Error(failure = Failure.BadRequest)
            else->{
                val body = this.body()
                return if(body == null){
                    ResponseState.Error(failure = Failure.BadRequest)
                }else{
                    ResponseState.Success(data = body)
                }
            }
        }
    }catch (e: IOException){
        ResponseState.Error(failure = Failure.NetworkConnection)
    }catch (t: Throwable){
        ResponseState.Error(failure = Failure.UnHandleError())
    }
}