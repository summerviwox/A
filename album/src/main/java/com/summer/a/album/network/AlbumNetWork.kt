package com.summer.a.album.network

import com.summer.a.album.bean.Picture
import com.summer.a.network.NetWork

//java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
//Failed to connect to /192.168.20.144:8888
object AlbumNetWork {


    private val apiService:ApiService by lazy {
        NetWork.retrofit.create(ApiService::class.java)
    }

    /**
     * 获取所有图片
     */
    suspend fun getAllPictures():ArrayList<Picture> {
        var result = apiService.getAllPictures(startTime = 1668845279069.toString())
        return result?.data ?: arrayListOf()
    }
}