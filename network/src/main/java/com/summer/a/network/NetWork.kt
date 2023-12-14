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

    val apiService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    /**
     * 网络请求框架
     */
    private val retrofit:Retrofit by lazy {
        var client = OkHttpClient.Builder().run {
            connectTimeout(100000, TimeUnit.SECONDS)
            readTimeout(100000, TimeUnit.SECONDS).
            writeTimeout(100000, TimeUnit.SECONDS)
        }.addInterceptor {
            it.proceed(it.request().newBuilder().header("token","summerviwox").build())
        }.build()

        Retrofit.Builder()
            .baseUrl("http://www.summerviwox.com/record/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}