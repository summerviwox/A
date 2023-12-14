package com.summer.a.album.network

import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit 网络请求参数注解@Path @Field @Query 等使用 https://blog.csdn.net/qq_33210042/article/details/103179989
interface ApiService {

    @GET("picture/getAllPictures")
    suspend fun getAllPictures(@Query("startTime") startTime:String? = null, @Query("endTime") endTime:String? = null):com.summer.a.album.bean.ListData<com.summer.a.album.bean.Picture>?
}