package com.rsupport.mobile1.test.server

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

internal class ApiServiceImpl {
    companion object {
        fun getService(): ApiService {
            val client = getDefaultOkHttpClient()

            return Retrofit.Builder()
                .baseUrl(ApiConst.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   // for RxJava
                .build()
                .create(ApiService::class.java)
        }

        private fun getDefaultOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .connectTimeout(ApiConst.API_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConst.API_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApiConst.API_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .hostnameVerifier(HostnameVerifier{ hostname, session -> true })

                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var request: Request = chain.request()

                        //Interceptor 사용해서 500 에러 발생 시 재요청한다. (.addInterceptor부터)
                        //참고 사이트 : https://stackoverflow.com/questions/24562716/how-to-retry-http-requests-with-okhttp-retrofit
                        //try the request
                        var response: Response = chain.proceed(request)
                        if (response.code == 500) {
                            response.close()
                            //try the request
                            Thread.sleep(500)
                            response = chain.proceed(request)
                        }
                        return response
                    }
                })

                .build()
        }
    }
}