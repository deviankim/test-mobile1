package com.blue.data.remote.util

import android.util.Log
import com.blue.data.remote.state.ResponseState
import com.blue.domain.util.Constants
import com.blue.domain.util.Failure
import retrofit2.Response
import java.io.IOException

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