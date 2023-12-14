package com.summer.a.network

import com.blankj.utilcode.util.LogUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络层
 */
object NetWork {

    /**
     * 网络请求框架
     */
    val retrofit:Retrofit by lazy {
        var client = OkHttpClient.Builder().run {
            connectTimeout(100000, TimeUnit.SECONDS)
            readTimeout(100000, TimeUnit.SECONDS).
            writeTimeout(100000, TimeUnit.SECONDS)
        }.addInterceptor {
            try {
                it.proceed(it.request().newBuilder().header("token","summerviwox").build())
            } catch (e:Exception) {
                LogUtils.e(e)
                null
            }
        }.build()

        Retrofit.Builder()
            .baseUrl("http://www.summerviwox.com/record/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}