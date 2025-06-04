package com.summer.network

import com.summer.bean.picture.ListData
import com.summer.bean.picture.Picture
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit 网络请求参数注解@Path @Field @Query 等使用 https://blog.csdn.net/qq_33210042/article/details/103179989
interface ApiService {

    @GET("picture/getAllPictures")
    fun getAllPictures(@Query("startTime") startTime:String? = null, @Query("endTime") endTime:String? = null):Call<ListData<Picture>>

}