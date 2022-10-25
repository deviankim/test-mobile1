package com.rsupport.mobile1.test.server

import android.content.Context
import android.content.SharedPreferences
import com.rsupport.mobile1.test.server.resp.RespSearchImages
import com.rsupport.mobile1.test.server.resp.RespSignIn
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

//통합 Api 통신 클래스
class ApiConnector private constructor(private var context: Context) {
    companion object {
        const val TAG = "API_CONNECTOR"

        //싱글톤 패턴. 이미 인스턴스가 존재하면 인자로 받은 컨텍스트로만 교체한다.
        @Volatile
        private var instance: ApiConnector? = null

        @Synchronized
        fun getInstance(context: Context): ApiConnector {
            if (instance == null) {
                instance = ApiConnector(context)
            } else {
                instance!!.context = context
            }
            return instance!!
        }
    }

    private val server = ApiServiceImpl.getService()

    //SharedPreference 관리 클래스
    val tokenSharedPreference: TokenSharedPreference = TokenSharedPreference(context)

    //토큰이 SharedPreference에 존재하는지 여부를 반환하는 메서드
    fun checkTokenExists(): Boolean {
        return tokenSharedPreference.checkTokenExists()
    }

    //액세스 토큰/리프레시 토큰을 저장하는 메서드
    fun saveTokens(accessToken: String?, refreshToken: String?) {
        tokenSharedPreference.saveTokens(accessToken, refreshToken)
    }

    //액세스 토큰/리프레시 토큰을 비우는 메서드
    fun clearTokens() {
        tokenSharedPreference.clearTokens()
    }

    //로그인
    fun signIn(
        onSuccess: (RespSignIn) -> Unit,
        onFailed: (Int) -> Unit
    ) {
        val successConsumer = Consumer<RespSignIn>({
            onSuccess(it)
        })
        val failedConsumer = Consumer<Throwable>({

        })
        server.signIn()
            .subscribeOn(Schedulers.io())
            .subscribe(successConsumer, failedConsumer)
    }

    //이미지 검색
    fun searchImages(
        onSuccess: (RespSearchImages) -> Unit,
        onFailed: () -> Unit,
        phrase: String,
        page_num: Int,
        page_size: Int
    ) {
        var accessToken = tokenSharedPreference.getAccessToken()
        val successConsumer = Consumer<RespSearchImages>({
            onSuccess(it)
        })
        val failedConsumer = Consumer<Throwable>({
            onFailed()
        })
        //auth 값.("Bearer " + 액세스 토큰)
        val auth: String = "Bearer $accessToken"
        server.searchImages(auth, phrase, page_num, page_size)
            .subscribeOn(Schedulers.io())
            .subscribe(successConsumer, failedConsumer)
    }

    //토큰 관리하는 SharedPreference 여기에 만들기. 로그아웃 시 clear하는 function 작동.
    inner class TokenSharedPreference(context: Context) {
        val pref: SharedPreferences

        init {
            pref = context.getSharedPreferences("TOKEN_INFO", 0)
        }


        //access_token을 출력하는 메서드.
        fun getAccessToken(): String? {
            return pref.getString("access_token", null)
        }

        //access_token을 저장하는 메서드.
        fun setAccessToken(accessToken: String?) {
            val edit = pref.edit()
            edit.putString("access_token", accessToken)
            edit.apply()
        }

        //refresh_token을 출력하는 메서드.
        fun getRefreshToken(): String? {
            return pref.getString("refresh_token", null)
        }

        //refresh_token을 저장하는 메서드.
        fun setRefreshToken(refreshToken: String?) {
            val edit = pref.edit()
            edit.putString("refresh_token", refreshToken)
            edit.apply()
        }

        //Access Token, Refresh Token을 가져오는 메서드
        fun getTokens(): Array<String?> {
            val accessToken = getAccessToken()
            val refreshToken = getRefreshToken()
            return arrayOf(accessToken, refreshToken)
        }

        //토큰이 SharedPreference에 존재하는지 여부를 반환하는 메서드
        fun checkTokenExists(): Boolean {
            val accessToken = getAccessToken()
            return accessToken != null
        }

        //Access Token, Refresh Token을 저장하는 메서드
        fun saveTokens(accessToken: String?, refreshToken: String?) {
            setAccessToken(accessToken)
            setRefreshToken(refreshToken)
        }

        //access_token과 refresh_token 정보를 모두 비우는 메서드
        fun clearTokens() {
            val edit = pref.edit()
            edit.remove("access_token")
            edit.remove("refresh_token")
            edit.apply()
        }
    }
}