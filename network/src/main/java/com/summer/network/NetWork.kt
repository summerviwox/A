package com.summer.network

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.Collections
import java.util.concurrent.TimeUnit


/**
 * 网络层
 */
object NET {
    private val client = OkHttpClient.Builder().run {
        connectTimeout(60*60, TimeUnit.SECONDS)
        readTimeout(60*60, TimeUnit.SECONDS)
        writeTimeout(60*60, TimeUnit.SECONDS)
        callTimeout(60*60,TimeUnit.SECONDS)
        writeTimeout(60*60,TimeUnit.SECONDS)
        protocols( Collections.singletonList(Protocol.HTTP_1_1) )
    }.addInterceptor {
        it.proceed(it.request().newBuilder().header("token","summerviwox").build())
    }.build()
    private val mediaType: MediaType = "application/json".toMediaType()
    suspend fun get(path: String,vararg args: Pair<String,String?>):String?{
        return withContext(Dispatchers.IO){
            var builder =HttpUrl
                        .Builder()
                        .scheme("https")
                        .host("www.summerviwox.com")
                        .addPathSegment("record")
                        .addPathSegments(path)
            args.forEach {
                builder.addQueryParameter(it.first,it.second)
            }
            val request = Request.Builder()
                .url(builder.build())
                .get()
                .build()
            try {
                client.newCall(request).execute().body?.string().also {
                    LogUtils.e(it)
                }
            }catch (e:Exception){
                LogUtils.e(e)
                null
            }
        }
    }
    suspend fun post(path:String,any:Any):String?{
        return withContext(Dispatchers.IO){
            val body =GsonUtils.toJson(any).toRequestBody(mediaType)
            val request = Request.Builder()
                .url("http://www.summerviwox.com/record/${path}")
                .post(body)
                .build()
            client.newCall(request).execute().body?.string()
        }
    }
}